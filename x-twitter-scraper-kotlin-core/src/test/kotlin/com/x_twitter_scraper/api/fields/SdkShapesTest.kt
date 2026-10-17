// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.fields

import com.fasterxml.jackson.annotation.JsonProperty
import com.x_twitter_scraper.api.core.MultipartField
import com.x_twitter_scraper.api.core.Params
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.checkRequired
import com.x_twitter_scraper.api.core.http.Headers
import com.x_twitter_scraper.api.core.http.HttpMethod
import com.x_twitter_scraper.api.core.http.HttpRequest
import com.x_twitter_scraper.api.core.http.QueryParams
import com.x_twitter_scraper.api.core.http.uploadBody
import com.x_twitter_scraper.api.core.jsonMapper
import com.x_twitter_scraper.api.models.EventType
import com.x_twitter_scraper.api.models.PaginatedTweets
import com.x_twitter_scraper.api.models.PaginatedUsers
import com.x_twitter_scraper.api.models.SearchTweet
import com.x_twitter_scraper.api.models.account.AccountUpdateLocaleResponse
import com.x_twitter_scraper.api.models.guestwallets.GuestWalletRetrieveStatusResponse
import com.x_twitter_scraper.api.models.support.tickets.TicketCreateParams
import com.x_twitter_scraper.api.models.support.tickets.TicketReplyParams
import com.x_twitter_scraper.api.models.x.media.MediaUploadParams
import com.x_twitter_scraper.api.models.x.profile.ProfileUpdateAvatarParams
import com.x_twitter_scraper.api.models.x.profile.ProfileUpdateBannerParams
import java.io.InputStream
import java.nio.file.Path
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir

internal class SdkShapesTest {

    @TempDir lateinit var directory: Path

    private fun operation(label: String): SdkOperation = operations.single { it.label == label }

    @Test
    fun recordsTheMethodAndPathEachOperationSends() {
        assertThat(operations).hasSize(183)
        assertThat(operations.map { it.label }.distinct()).hasSize(180)
        assertThat(
                operations.map {
                    val contentType = it.contentType?.substringBefore(';')
                    Triple(it.method, it.pathSegments, contentType)
                }
            )
            .doesNotHaveDuplicates()
        assertThat(operation("x().spaces().search").method).isEqualTo("GET")
        assertThat(operation("x().spaces().search").pathSegments)
            .containsExactly("x", "spaces", "search")
        assertThat(operation("x().tweets().retrieve").pathSegments)
            .containsExactly("x", "tweets", "zz-sample-a")
    }

    @Test
    fun readsQueryParamsByTheirWireNamesWithEnumsAndRequiredFlags() {
        val query = operation("x().spaces().search").query.props

        assertThat(query["q"]).isEqualTo(Prop(Shape.Leaf(), true, null))
        assertThat(query["queryType"])
            .isEqualTo(Prop(Shape.Leaf(setOf("Latest", "Top", "Live", "Upcoming")), false, null))
        assertThat(operation("x().tweets().retrieve").query.props.keys).contains("fields", "omit")
        assertThat(operation("x().getTrendsHistory").query.props.keys).contains("from")
    }

    @Test
    fun readsJsonAndMultipartBodies() {
        val ticket = operation("support().tickets().create").body as Shape.Fields
        val compose = operation("compose().create").body

        assertThat(ticket.props["body"]).isEqualTo(Prop(Shape.Leaf(), false, null))
        assertThat(ticket.props["subject"]).isEqualTo(Prop(Shape.Leaf(), true, null))
        assertThat(compose).isInstanceOf(Shape.AnyOf::class.java)
        assertThat(operation("x().tweets().retrieve").body).isNull()
    }

    @Test
    fun leavesStreamsWithoutAJsonResponse() {
        assertThat(operation("events().stream").response).isNull()
        assertThat(operation("x().tweets().retrieve").response)
            .isInstanceOf(Shape.Fields::class.java)
    }

    @Test
    fun readsAlternativeUploadInputs() {
        val reader = SdkShapes()
        listOf(
                MediaUploadParams.Body::class.java,
                ProfileUpdateAvatarParams.Body::class.java,
                ProfileUpdateBannerParams.Body::class.java,
            )
            .forEach { model ->
                val props = (reader.shapeOf(model) as Shape.Fields).props

                assertThat(props.getValue("account").required).isTrue()
                assertThat(props.getValue("url").required).isFalse()
                assertThat(props.getValue("file").required).isFalse()
            }
    }

    @Test
    fun readsTicketInputsWithoutMakingEitherRequired() {
        val reader = SdkShapes()
        listOf(TicketCreateParams.Body::class.java, TicketReplyParams.Body::class.java).forEach {
            model ->
            val props = (reader.shapeOf(model) as Shape.Fields).props

            assertThat(props.getValue("body").required).isFalse()
            assertThat(props.getValue("attachments").required).isFalse()
        }
        listOf("support().tickets().create", "support().tickets().reply").forEach { label ->
            val body = operation(label).body as Shape.Fields

            assertThat(body.props.getValue("body").required).isFalse()
            assertThat(body.props.getValue("attachments").required).isFalse()
        }
    }

    @Test
    fun recordsTheUploadBodyTypeAndItsSelectedInput() {
        listOf("x().media().upload", "x().profile().updateAvatar", "x().profile().updateBanner")
            .forEach { label ->
                val uploads = operations.filter { it.label == label }
                val json = uploads.single { it.contentType == "application/json" }
                val multipart = uploads.single {
                    it.contentType?.startsWith("multipart/form-data;") == true
                }
                val jsonBody = json.body as Shape.Fields
                val multipartBody = multipart.body as Shape.Fields

                assertThat(uploads).hasSize(2)
                assertThat(jsonBody.props.getValue("url").required).isTrue()
                assertThat(jsonBody.props).doesNotContainKeys("file", "is_long_video")
                assertThat(multipartBody.props.getValue("file").required).isTrue()
                assertThat(multipartBody.props).doesNotContainKey("url")
                if (label == "x().media().upload") {
                    assertThat(multipartBody.props.getValue("is_long_video").required).isFalse()
                }
            }
        assertThat(operation("support().tickets().create").contentType)
            .startsWith("multipart/form-data;")
        assertThat(operation("x().tweets().retrieve").contentType).isNull()
    }

    @Test
    fun reportsDependentFieldsInJsonWithoutABuilderRule() {
        val spec =
            jsonMapper()
                .readTree(
                    """
                    {"paths": {"/api/v1/x/media": {"post": {"requestBody": {"content": {
                      "multipart/form-data": {"schema": {
                        "type": "object", "required": ["account", "file"], "properties": {
                          "account": {"type": "string"}, "file": {"type": "string"},
                          "is_long_video": {"type": "boolean"}
                        }
                      }},
                      "application/json": {"schema": {
                        "type": "object", "required": ["account", "url"], "properties": {
                          "account": {"type": "string"}, "url": {"type": "string"}
                        }
                      }}
                    }}, "responses": {}}}}}
                    """
                )
        val accepted = directory.resolve("accepted.json").toFile().apply { writeText("[]") }
        val service = UnrestrictedUploadService()
        val unrestricted = SdkShapes().operations(service)
        val json = unrestricted.single { it.contentType == "application/json" }
        val multipart = unrestricted.single {
            it.contentType?.startsWith("multipart/form-data;") == true
        }

        assertThat((json.body as Shape.Fields).props).containsKey("is_long_video")
        assertThat((multipart.body as Shape.Fields).props).containsKey("is_long_video")
        assertThat(service.bodies).hasSize(2)
        service.bodies.forEach {
            assertThat(it.getValue("is_long_video").value.asBoolean()).isTrue()
        }
        val report = ApiFields(spec, accepted) { unrestricted }.run()

        assertThat(report.passed).isFalse()
        assertThat(report.lines)
            .containsExactly(
                "POST /api/v1/x/media request extra is_long_video",
                "Fix each field above in the SDK, or list it in accepted.json with a reason.",
            )
        assertThat(ApiFields(spec, accepted) { operations }.run())
            .isEqualTo(Report(true, listOf("The SDK's fields match the API for 1 operation.")))
    }

    @Test
    fun readsRequiredAndNullableFlagsFromTheBuildersAndGetters() {
        val response = operation("x().tweets().retrieve").response as Shape.Fields
        val tweet = response.props.getValue("tweet")
        val fields = tweet.shape as Shape.Fields

        assertThat(tweet.required).isTrue()
        assertThat(tweet.nullable).isFalse()
        assertThat(fields.props.getValue("id")).isEqualTo(Prop(Shape.Leaf(), true, false))
        assertThat(fields.props.getValue("lang").required).isFalse()
        assertThat(fields.props.getValue("lang").nullable).isNull()
    }

    @Test
    fun readsNumericEnumsAndConstants() {
        val reader = SdkShapes()
        val status = reader.shapeOf(GuestWalletRetrieveStatusResponse::class.java) as Shape.Fields
        val locale = reader.shapeOf(AccountUpdateLocaleResponse::class.java) as Shape.Fields

        assertThat(status.props.getValue("poll_after_seconds").shape)
            .isEqualTo(Shape.Leaf(setOf("2")))
        assertThat(locale.props.getValue("success"))
            .isEqualTo(Prop(Shape.Leaf(setOf("true")), null, null))
    }

    @Test
    fun readsAdditionalFieldsWithoutMakingThemRequired() {
        val reader = SdkShapes()
        val tweets = reader.shapeOf(PaginatedTweets::class.java) as Shape.Fields
        val users = reader.shapeOf(PaginatedUsers::class.java) as Shape.Fields
        val tweet = reader.shapeOf(SearchTweet::class.java) as Shape.Fields

        assertThat(tweets.props.getValue("skipped").required).isFalse()
        assertThat(users.props.getValue("scanLimit").required).isFalse()
        assertThat(users.props.getValue("diagnostic").required).isFalse()
        assertThat(tweet.props.getValue("hiddenByAuthor"))
            .isEqualTo(Prop(Shape.Leaf(), false, null))
        assertThat(operation("x().users().retrieveBatch").query.props.getValue("usernames"))
            .isEqualTo(Prop(Shape.Leaf(), false, null))
    }

    @Test
    fun readsTheAdditionalMonitorEventTypes() {
        val eventType = SdkShapes().shapeOf(EventType::class.java) as Shape.Leaf

        assertThat(eventType.values)
            .contains(
                "tweet.deleted",
                "broadcast.started",
                "space.started",
                "following.added",
                "following.removed",
                "follower.added",
                "follower.removed",
                "profile.followers.changed",
                "profile.following.changed",
            )
    }

    class UnrestrictedUploadService {

        val bodies = mutableListOf<Map<String, MultipartField<*>>>()

        fun upload(params: UnrestrictedUploadParams, requestOptions: RequestOptions) {
            val body = params._body()
            bodies += body
            SdkShapes.RecordingHttpClient()
                .execute(
                    HttpRequest.builder()
                        .method(HttpMethod.POST)
                        .baseUrl("https://example.com")
                        .addPathSegments("x", "media")
                        .body(uploadBody(jsonMapper(), body, params.file() != null))
                        .build(),
                    requestOptions,
                )
        }
    }

    class UnrestrictedUploadParams(private val body: Body) : Params {

        fun file(): InputStream? = body._file().value.asKnown()

        fun _body(): Map<String, MultipartField<*>> =
            mapOf(
                    "account" to body._account(),
                    "url" to body._url(),
                    "file" to body._file(),
                    "is_long_video" to body._isLongVideo(),
                )
                .filterValues { !it.value.isMissing() && !it.value.isNull() }

        override fun _headers(): Headers = Headers.builder().build()

        override fun _queryParams(): QueryParams = QueryParams.builder().build()

        companion object {
            fun builder() = Builder()
        }

        class Builder {

            private val body = Body.builder()

            fun account(account: String) = apply { body.account(account) }

            fun url(url: String) = apply { body.url(url) }

            fun file(file: InputStream) = apply { body.file(file) }

            fun isLongVideo(isLongVideo: Boolean) = apply { body.isLongVideo(isLongVideo) }

            fun build(): UnrestrictedUploadParams = UnrestrictedUploadParams(body.build())
        }

        class Body(
            private val account: String,
            private val url: String?,
            private val file: InputStream?,
            private val isLongVideo: Boolean?,
        ) {

            @JsonProperty("account")
            fun _account(): MultipartField<String> = MultipartField.of(account)

            @JsonProperty("url") fun _url(): MultipartField<String> = MultipartField.of(url)

            @JsonProperty("file") fun _file(): MultipartField<InputStream> = MultipartField.of(file)

            @JsonProperty("is_long_video")
            fun _isLongVideo(): MultipartField<Boolean> = MultipartField.of(isLongVideo)

            companion object {
                fun builder() = Builder()
            }

            class Builder {

                private var account: String? = null
                private var url: String? = null
                private var file: InputStream? = null
                private var isLongVideo: Boolean? = null

                fun account(account: String) = apply { this.account = account }

                fun url(url: String) = apply { this.url = url }

                fun file(file: InputStream) = apply { this.file = file }

                fun isLongVideo(isLongVideo: Boolean) = apply { this.isLongVideo = isLongVideo }

                fun build(): Body {
                    check((url != null) xor (file != null)) {
                        "Exactly one of `url` or `file` must be set"
                    }
                    return Body(checkRequired("account", account), url, file, isLongVideo)
                }
            }
        }
    }

    companion object {
        private val operations: List<SdkOperation> by lazy { SdkShapes().operations() }
    }
}
