// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.fields

import com.fasterxml.jackson.annotation.JsonProperty
import com.x_twitter_scraper.api.client.XTwitterScraperClient
import com.x_twitter_scraper.api.client.XTwitterScraperClientImpl
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.Enum
import com.x_twitter_scraper.api.core.JsonField
import com.x_twitter_scraper.api.core.JsonMissing
import com.x_twitter_scraper.api.core.JsonNull
import com.x_twitter_scraper.api.core.JsonValue
import com.x_twitter_scraper.api.core.MultipartField
import com.x_twitter_scraper.api.core.Params
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpClient
import com.x_twitter_scraper.api.core.http.HttpRequest
import com.x_twitter_scraper.api.core.http.HttpResponse
import com.x_twitter_scraper.api.core.http.QueryParams
import com.x_twitter_scraper.api.errors.XTwitterScraperInvalidDataException
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.lang.reflect.Modifier
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.lang.reflect.WildcardType
import java.time.LocalDate
import java.time.OffsetDateTime

internal data class SdkOperation(
    val label: String,
    val method: String,
    val pathSegments: List<String>,
    val query: Shape.Fields,
    val body: Shape?,
    val response: Shape?,
)

internal class SdkShapes(private val client: XTwitterScraperClient = recordingClient()) {

    private val models = mutableMapOf<Class<*>, Shape>()
    private val requiredNames = mutableMapOf<Class<*>, Set<String>>()

    fun operations(): List<SdkOperation> =
        services(client, "").flatMap { (label, service) ->
            service.javaClass.methods
                .filter(::isOperation)
                .sortedBy { it.name }
                .map { operation("$label${it.name}", service, it) }
        }

    private fun services(target: Any, label: String): List<Pair<String, Any>> =
        listOf(label to target) +
            target.javaClass.methods
                .filter {
                    it.parameterCount == 0 &&
                        !it.isSynthetic &&
                        it.returnType.isInterface &&
                        it.returnType.name.startsWith(SERVICES) &&
                        !it.returnType.name.contains("WithRawResponse")
                }
                .sortedBy { it.name }
                .flatMap { services(it.invoke(target), "$label${it.name}().") }

    private fun isOperation(method: Method): Boolean =
        !method.isSynthetic &&
            !method.isBridge &&
            !Modifier.isStatic(method.modifiers) &&
            method.parameterCount == 2 &&
            Params::class.java.isAssignableFrom(method.parameterTypes[0]) &&
            method.parameterTypes[1] == RequestOptions::class.java

    private fun operation(label: String, service: Any, method: Method): SdkOperation {
        val paramsClass = method.parameterTypes[0]
        val required = mutableSetOf<String>()
        repeat(MAX_REQUIRED) {
            val params =
                try {
                    buildParams(paramsClass, required.associateWith { Sample.A })
                } catch (e: InvocationTargetException) {
                    required += requiredName(e.cause, label)
                    return@repeat
                }
            try {
                method.invoke(service, params, RequestOptions.none())
            } catch (e: InvocationTargetException) {
                val cause = e.cause
                if (cause is Recorded) {
                    return SdkOperation(
                        label,
                        cause.request.method.name,
                        cause.request.pathSegments,
                        query(paramsClass, required),
                        body(paramsClass),
                        response(method.genericReturnType),
                    )
                }
                required += requiredName(cause, label)
                return@repeat
            }
            throw IllegalStateException("$label returned without sending a request.")
        }
        throw IllegalStateException("$label needs more than $MAX_REQUIRED required params.")
    }

    private fun query(paramsClass: Class<*>, required: Set<String>): Shape.Fields {
        val base = required.associateWith<String, Sample> { Sample.A }
        val props = linkedMapOf<String, Prop>()
        typedSetters(builderClass(paramsClass)).forEach { (name, setter) ->
            val first = queryOf(paramsClass, base + (name to Sample.A))
            val second = queryOf(paramsClass, base + (name to Sample.B))
            (first.keys() + second.keys())
                .filter { first.values(it) != second.values(it) && !it.contains('[') }
                .forEach { wire ->
                    props[wire] =
                        Prop(shapeOf(setter.genericParameterTypes[0]), name in required, null)
                }
        }
        return Shape.Fields(props)
    }

    private fun queryOf(paramsClass: Class<*>, values: Map<String, Sample>): QueryParams =
        (buildParams(paramsClass, values) as Params)._queryParams()

    private fun body(paramsClass: Class<*>): Shape? {
        val bodyMethod =
            paramsClass.methods.singleOrNull { it.name == "_body" && it.parameterCount == 0 }
                ?: return null
        val bodyClass =
            paramsClass.declaredClasses.singleOrNull { it.simpleName == "Body" }
                ?: bodyMethod.returnType.takeIf { isModel(it) }
                ?: return null
        return shapeOf(bodyClass)
    }

    private fun response(type: Type): Shape? {
        val raw = rawClass(type)
        if (
            raw == Void.TYPE ||
                raw == Unit::class.java ||
                HttpResponse::class.java.isAssignableFrom(raw) ||
                raw.name == STREAM_RESPONSE
        ) {
            return null
        }
        return shapeOf(type)
    }

    fun shapeOf(type: Type): Shape {
        if (type is WildcardType) {
            return shapeOf(type.upperBounds.first())
        }
        val raw = rawClass(type)
        return when {
            raw in LEAVES -> Shape.Leaf()
            List::class.java.isAssignableFrom(raw) ->
                Shape.ListOf(shapeOf((type as ParameterizedType).actualTypeArguments[0]))
            Map::class.java.isAssignableFrom(raw) -> Shape.Free
            JsonValue::class.java.isAssignableFrom(raw) -> Shape.Unknown
            else -> shapeOf(raw)
        }
    }

    private fun shapeOf(model: Class<*>): Shape {
        models[model]?.let {
            return it
        }
        models[model] = Shape.Cycle
        val shape =
            when {
                Enum::class.java.isAssignableFrom(model) -> Shape.Leaf(enumValues(model))
                model.declaredClasses.any { it.simpleName == "Visitor" } ->
                    Shape.AnyOf(
                        model.methods
                            .filter {
                                it.name.startsWith("as") &&
                                    it.parameterCount == 0 &&
                                    model.methods.any { other ->
                                        other.name == "is" + it.name.removePrefix("as")
                                    }
                            }
                            .sortedBy { it.name }
                            .map { shapeOf(it.genericReturnType) }
                    )
                rawGetters(model).isEmpty() ->
                    if (model.methods.any { it.name == "_additionalProperties" }) Shape.Free
                    else throw IllegalStateException("Cannot read the fields of ${model.name}.")
                else -> fields(model)
            }
        models[model] = shape
        return shape
    }

    private fun fields(model: Class<*>): Shape.Fields {
        val required = requiredProperties(model)
        val props =
            rawGetters(model).associate { getter ->
                val wire = getter.getAnnotation(JsonProperty::class.java).value
                val property = getter.name.removePrefix("_")
                val type = getter.genericReturnType
                val raw = rawClass(type)
                val prop =
                    if (JsonValue::class.java.isAssignableFrom(raw)) {
                        Prop(
                            constant(model, property)?.let { Shape.Leaf(setOf(it)) }
                                ?: Shape.Unknown,
                            null,
                            null,
                        )
                    } else {
                        val inner = (type as ParameterizedType).actualTypeArguments[0]
                        val isRequired =
                            property in required || property.removeSuffix("_") in required
                        val nullable =
                            if (isRequired && JsonField::class.java.isAssignableFrom(raw)) {
                                nullable(model, property, required)
                            } else {
                                null
                            }
                        Prop(shapeOf(inner), isRequired, nullable)
                    }
                wire to prop
            }
        return Shape.Fields(props)
    }

    private fun rawGetters(model: Class<*>): List<Method> =
        model.methods
            .filter {
                it.parameterCount == 0 &&
                    it.name.startsWith("_") &&
                    it.isAnnotationPresent(JsonProperty::class.java) &&
                    (JsonField::class.java.isAssignableFrom(it.returnType) ||
                        MultipartField::class.java.isAssignableFrom(it.returnType))
            }
            .sortedBy { it.name }

    private fun requiredProperties(model: Class<*>): Set<String> =
        requiredNames.getOrPut(model) {
            val names = mutableSetOf<String>()
            repeat(MAX_REQUIRED) {
                try {
                    build(model, names.associateWith { Sample.MISSING })
                    return@getOrPut names
                } catch (e: InvocationTargetException) {
                    names += requiredName(e.cause, model.name)
                }
            }
            throw IllegalStateException(
                "${model.name} needs more than $MAX_REQUIRED required fields."
            )
        }

    private fun nullable(model: Class<*>, property: String, required: Set<String>): Boolean {
        val value =
            build(model, required.associateWith { Sample.MISSING } + (property to Sample.NULL))
        val getter = model.methods.single { it.name == property && it.parameterCount == 0 }
        return try {
            getter.invoke(value) == null
        } catch (e: InvocationTargetException) {
            if (e.cause is XTwitterScraperInvalidDataException) false else throw e
        }
    }

    private fun constant(model: Class<*>, property: String): String? {
        val builder = newBuilder(model)
        val field =
            listOf(property, property.removeSuffix("_")).firstNotNullOfOrNull { name ->
                builder.javaClass.declaredFields.firstOrNull { it.name == name }
            } ?: return null
        field.isAccessible = true
        val value = field.get(builder) as? JsonValue ?: return null
        return when {
            value is JsonMissing -> null
            value is JsonNull -> "null"
            else -> value.asString() ?: value.toString()
        }
    }

    private fun enumValues(model: Class<*>): Set<String> {
        val companion = model.getField("Companion").get(null)
        return companion.javaClass.methods
            .filter {
                it.parameterCount == 0 && it.returnType == model && it.name.startsWith("get")
            }
            .map { getter ->
                val value = getter.invoke(companion)
                val raw = model.getMethod("_value").invoke(value) as JsonField<*>
                raw.asString()
                    ?: raw.asNumber()?.toString()
                    ?: throw IllegalStateException(
                        "${model.name} has a value that is not a string or number."
                    )
            }
            .toSet()
    }

    private fun buildParams(paramsClass: Class<*>, values: Map<String, Sample>): Any =
        build(paramsClass, values)

    private fun build(model: Class<*>, values: Map<String, Sample>): Any {
        val builder = newBuilder(model)
        values.forEach { (name, sample) -> set(builder, name, sample) }
        return builder.javaClass.getMethod("build").invoke(builder)
    }

    private fun set(builder: Any, name: String, sample: Sample) {
        val setters =
            builder.javaClass.methods.filter {
                it.name == name && it.parameterCount == 1 && it.returnType == builder.javaClass
            }
        val raw =
            setters.firstOrNull { JsonField::class.java.isAssignableFrom(it.parameterTypes[0]) }
                ?: setters.firstOrNull {
                    MultipartField::class.java.isAssignableFrom(it.parameterTypes[0])
                }
        val typed = typedSetters(builder.javaClass)[name]
        when {
            sample == Sample.MISSING && raw != null ->
                raw.invoke(builder, rawValue(raw, JsonMissing.of()))
            sample == Sample.NULL && raw != null ->
                raw.invoke(builder, rawValue(raw, JsonNull.of()))
            typed != null -> typed.invoke(builder, sampleOf(typed.genericParameterTypes[0], sample))
            raw != null -> raw.invoke(builder, rawValue(raw, JsonMissing.of()))
            setters.isNotEmpty() ->
                setters.first().let {
                    it.invoke(builder, minimalValue(rawClass(it.genericParameterTypes[0])))
                }
            else ->
                throw IllegalStateException(
                    "${builder.javaClass.name} has no setter for $name that this check can fill."
                )
        }
    }

    private fun minimalValue(type: Class<*>): Any {
        if (type.declaredClasses.any { it.simpleName == "Visitor" }) {
            val companion = type.getField("Companion").get(null)
            val factory =
                companion.javaClass.methods
                    .filter { it.name.startsWith("of") && it.parameterCount == 1 }
                    .minByOrNull { it.name }
                    ?: throw IllegalStateException(
                        "${type.name} has no factory this check can call."
                    )
            val argument = factory.genericParameterTypes[0]
            val value =
                if (canSample(argument)) sampleOf(argument, Sample.A)
                else minimalValue(rawClass(argument))
            return factory.invoke(companion, value)
        }
        return build(type, requiredProperties(type).associateWith { Sample.MISSING })
    }

    private fun rawValue(setter: Method, value: JsonValue): Any =
        if (MultipartField::class.java.isAssignableFrom(setter.parameterTypes[0])) {
            MultipartField.of(if (value is JsonNull) null else value)
        } else {
            value
        }

    private fun typedSetters(builderClass: Class<*>): Map<String, Method> =
        builderClass.methods
            .filter {
                it.parameterCount == 1 &&
                    it.returnType == builderClass &&
                    !it.isSynthetic &&
                    it.name !in BUILDER_PLUMBING &&
                    BUILDER_PLUMBING_PREFIXES.none { prefix -> it.name.startsWith(prefix) } &&
                    canSample(it.genericParameterTypes[0])
            }
            .groupBy { it.name }
            .mapValues { (_, overloads) ->
                overloads.firstOrNull { !it.parameterTypes[0].isPrimitive } ?: overloads.first()
            }

    private fun builderClass(model: Class<*>): Class<*> = newBuilder(model).javaClass

    private fun newBuilder(model: Class<*>): Any {
        model.methods
            .singleOrNull {
                Modifier.isStatic(it.modifiers) && it.name == "builder" && it.parameterCount == 0
            }
            ?.let {
                return it.invoke(null)
            }
        val companion = model.getField("Companion").get(null)
        return companion.javaClass.getMethod("builder").invoke(companion)
    }

    private fun canSample(type: Type): Boolean {
        val raw = rawClass(type)
        return when {
            raw in LEAVES -> true
            List::class.java.isAssignableFrom(raw) ->
                canSample((type as ParameterizedType).actualTypeArguments[0].let(::unwrap))
            Enum::class.java.isAssignableFrom(raw) -> true
            else -> false
        }
    }

    private fun sampleOf(type: Type, sample: Sample): Any {
        val raw = rawClass(type)
        val second = sample == Sample.B
        return when {
            raw == String::class.java -> if (second) "zz-sample-b" else "zz-sample-a"
            raw == Long::class.javaObjectType || raw == Long::class.javaPrimitiveType ->
                if (second) 2L else 1L
            raw == Int::class.javaObjectType || raw == Int::class.javaPrimitiveType ->
                if (second) 2 else 1
            raw == Double::class.javaObjectType || raw == Double::class.javaPrimitiveType ->
                if (second) 2.5 else 1.5
            raw == Boolean::class.javaObjectType || raw == Boolean::class.javaPrimitiveType ->
                !second
            raw == OffsetDateTime::class.java ->
                OffsetDateTime.parse(if (second) "2026-02-02T00:00:00Z" else "2026-01-01T00:00:00Z")
            raw == LocalDate::class.java ->
                LocalDate.parse(if (second) "2026-02-02" else "2026-01-01")
            raw == InputStream::class.java ->
                ByteArrayInputStream(byteArrayOf(if (second) 2 else 1))
            raw == ByteArray::class.java -> byteArrayOf(if (second) 2 else 1)
            List::class.java.isAssignableFrom(raw) ->
                listOf(sampleOf(unwrap((type as ParameterizedType).actualTypeArguments[0]), sample))
            Enum::class.java.isAssignableFrom(raw) -> {
                val companion = raw.getField("Companion").get(null)
                companion.javaClass
                    .getMethod("of", String::class.java)
                    .invoke(companion, if (second) "zz-sample-b" else "zz-sample-a")
            }
            else -> throw IllegalStateException("Cannot fill a value of type $type.")
        }
    }

    private fun requiredName(cause: Throwable?, owner: String): String {
        val message = cause?.message.orEmpty()
        return REQUIRED.find(message)?.groupValues?.get(1)
            ?: throw IllegalStateException(
                "$owner failed in a way this check cannot read: $message",
                cause,
            )
    }

    private fun isModel(type: Class<*>): Boolean = rawGetters(type).isNotEmpty()

    private class Recorded(val request: HttpRequest) : RuntimeException(null, null, false, false)

    private class RecordingHttpClient : HttpClient {

        override fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse =
            throw Recorded(request)

        override suspend fun executeAsync(
            request: HttpRequest,
            requestOptions: RequestOptions,
        ): HttpResponse = throw Recorded(request)

        override fun close() {}
    }

    private enum class Sample {
        A,
        B,
        MISSING,
        NULL,
    }

    companion object {
        private const val SERVICES = "com.x_twitter_scraper.api.services.blocking."
        private const val STREAM_RESPONSE = "com.x_twitter_scraper.api.core.http.StreamResponse"
        private const val MAX_REQUIRED = 64
        private val REQUIRED = Regex("^`(\\w+)` is required, but was not set$")
        private val BUILDER_PLUMBING = setOf("additionalBodyProperties")
        private val BUILDER_PLUMBING_PREFIXES =
            listOf(
                "additional",
                "putAdditional",
                "putAllAdditional",
                "removeAdditional",
                "removeAllAdditional",
                "replaceAdditional",
                "replaceAllAdditional",
                "add",
            )
        private val LEAVES =
            setOf(
                String::class.java,
                Long::class.javaObjectType,
                Long::class.javaPrimitiveType,
                Int::class.javaObjectType,
                Int::class.javaPrimitiveType,
                Double::class.javaObjectType,
                Double::class.javaPrimitiveType,
                Boolean::class.javaObjectType,
                Boolean::class.javaPrimitiveType,
                OffsetDateTime::class.java,
                LocalDate::class.java,
                InputStream::class.java,
                ByteArray::class.java,
            )

        fun recordingClient(): XTwitterScraperClient =
            XTwitterScraperClientImpl(
                ClientOptions.builder()
                    .httpClient(RecordingHttpClient())
                    .apiKey("api-fields")
                    .bearerToken("api-fields")
                    .maxRetries(0)
                    .build()
            )
    }
}

private fun rawClass(type: Type): Class<*> =
    when (type) {
        is Class<*> -> type
        is ParameterizedType -> type.rawType as Class<*>
        is WildcardType -> rawClass(type.upperBounds.first())
        else -> throw IllegalStateException("Cannot read the type $type.")
    }

private fun unwrap(type: Type): Type = if (type is WildcardType) type.upperBounds.first() else type
