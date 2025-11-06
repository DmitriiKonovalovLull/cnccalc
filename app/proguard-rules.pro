# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# ML Kit и Moshi/OkHttp/Retrofit/Room обычно не требуют спец-правил на дебаге.
# Если включишь minify — можно оставить пустым, либо добавить правила по докам библиотек.
# Правила для Dagger Hilt
-keep class com.konovalov.lull.cnccalc.Hilt_* { *; }
-keepclasseswithmembers class * {
    @dagger.hilt.internal.aggregatedroot.* *;
}

# Правила для Room
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *

# Правила для Retrofit
-keepattributes Signature, InnerClasses, EnclosingMethod
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
