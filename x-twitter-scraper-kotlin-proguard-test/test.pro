# Specify the entrypoint where ProGuard starts to determine what's reachable.
-keep class com.xquik.consumer.proguard.ProGuardCompatibilityTest {
    public static void main(java.lang.String[]);
}

# MethodHandle.invokeExact is signature-polymorphic, so no concrete descriptor exists in the JDK.
-dontwarn java.lang.invoke.MethodHandle

# Kotlin maps these JVM built-ins to java.util.concurrent.atomic classes at compile time.
-dontwarn kotlin.concurrent.atomics.AtomicArray
-dontwarn kotlin.concurrent.atomics.AtomicBoolean
-dontwarn kotlin.concurrent.atomics.AtomicInt
-dontwarn kotlin.concurrent.atomics.AtomicIntArray
-dontwarn kotlin.concurrent.atomics.AtomicLong
-dontwarn kotlin.concurrent.atomics.AtomicLongArray
-dontwarn kotlin.concurrent.atomics.AtomicReference

# These Kotlin compiler-only markers have no runtime class on the JVM.
-dontwarn kotlin.jvm.internal.EnhancedNullability
-dontwarn kotlin.reflect.jvm.internal.impl.types.model.AnnotationMarker

# This annotation is only present in Android builds of the coroutines debug agent.
-dontwarn android.annotation.SuppressLint

# OkHttp detects these optional TLS providers before loading their implementations.
-dontwarn org.bouncycastle.jsse.BCSSLParameters
-dontwarn org.bouncycastle.jsse.BCSSLSocket
-dontwarn org.bouncycastle.jsse.provider.BouncyCastleJsseProvider
-dontwarn org.openjsse.javax.net.ssl.SSLParameters
-dontwarn org.openjsse.javax.net.ssl.SSLSocket
-dontwarn org.openjsse.net.ssl.OpenJSSE

# Rules bundled with kotlinx-coroutines-core 1.11.0 for consumer shrinking.
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepclassmembers class kotlinx.coroutines.** {
    volatile <fields>;
}
-keepclassmembers class kotlin.coroutines.SafeContinuation {
    volatile <fields>;
}
-keepclassmembers class kotlinx.coroutines.flow.ReadonlySharedFlow {
    kotlinx.coroutines.Job job;
}
-keepclassmembers class kotlinx.coroutines.flow.ReadonlyStateFlow {
    kotlinx.coroutines.Job job;
}
