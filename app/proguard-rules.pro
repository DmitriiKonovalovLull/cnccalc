# ------------------------------
# Hilt (Dependency Injection)
# ------------------------------
-keepattributes *Annotation*

# Просто удаляем проблемную строку:
# -keep @dagger.hilt.InstallIn class *  ← КОММЕНТИРУЕМ ИЛИ УДАЛЯЕМ
-keep class dagger.hilt.internal.** { *; }

# Конкретные классы Hilt вместо широких правил
-keep @dagger.hilt.InstallIn class *
-keep @dagger.hilt.android.AndroidEntryPoint class *

-keep class dagger.hilt.internal.component.**
-keep class dagger.hilt.processor.internal.**

# Убрали проблемные строки 8, 13, 17
# -keep class dagger.hilt.InstallIn  ← УДАЛЕНО (строка 8)
# -keep class dagger.hilt.AndroidEntryPoint  ← УДАЛЕНО (строка 13)
# -keep class * extends dagger.hilt.android.flags.HiltWrapper_FragmentGetContextFix_ActivityContextEntryPoint  ← УДАЛЕНО (строка 17)

-keep class * extends dagger.hilt.internal.GeneratedComponentManagerHolder {
    public static ** getComponentManager();
}

# ------------------------------
# Room (Database)
# ------------------------------
# Более точные правила для Room
-keep,allowobfuscation class * extends androidx.room.RoomDatabase

# Сохраняем только классы с аннотацией @Entity
-keep @androidx.room.Entity class * {
    <fields>;
}

# Сохраняем классы DAO
-keep class * extends androidx.room.Dao

# ------------------------------
# Retrofit & Gson (Networking)
# ------------------------------
-keepattributes Signature, *Annotation*

# Более точные правила для Gson
-keep class com.google.gson.reflect.TypeToken
-keep class com.google.gson.stream.**
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Более точные правила для Retrofit
-keep class retrofit2.http.**
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

-keep class retrofit2.Call
-keep class retrofit2.Callback
-keep class retrofit2.Response
-keep class retrofit2.Retrofit

# ------------------------------
# TensorFlow Lite (Machine Learning)
# ------------------------------
# Более специфичные правила для TensorFlow Lite
-keep class org.tensorflow.lite.Interpreter
-keep class org.tensorflow.lite.Tensor
-keep class org.tensorflow.lite.support.model.Model
-keep class org.tensorflow.lite.support.** { *; }

# Основные классы TensorFlow Lite
-keep class org.tensorflow.lite.TensorFlowLite

# Убрали проблемные строки 72, 142
# -keep class org.tensorflow.lite.nnapi.NnApiDelegate  ← УДАЛЕНО (строка 72)
# -keep class org.tensorflow.lite.gpu.GpuDelegate  ← УДАЛЕНО (строка 142)

# ------------------------------
# ML Kit (Google Machine Learning)
# ------------------------------
# Более специфичные правила для ML Kit
-keep class com.google.mlkit.vision.**
-keep class com.google.mlkit.common.**
-keep class com.google.mlkit.common.model.**

# Вместо всех классов ML Kit, только основные модули
-keep class com.google.mlkit.vision.barcode.** { *; }
-keep class com.google.mlkit.vision.face.** { *; }
-keep class com.google.mlkit.vision.text.** { *; }

# ------------------------------
# Kotlin Coroutines
# ------------------------------
-keep class kotlinx.coroutines.android.AndroidDispatcherFactory

# ------------------------------
# Android Jetpack Components
# ------------------------------
# ViewBinding
-keep class * implements androidx.viewbinding.ViewBinding {
    public static ** inflate(android.view.LayoutInflater);
    public static ** bind(android.view.View);
}

# Navigation
-keep class * implements androidx.navigation.NavArgs

# Lifecycle
-keep class * extends androidx.lifecycle.ViewModel
-keepclassmembers class * extends androidx.lifecycle.ViewModel {
    <init>(...);
}

# ------------------------------
# Application Specific
# ------------------------------
# Сохраняем ваши основные классы приложения
-keep class com.example.cnccalc.CNCApplication
-keep class com.example.cnccalc.ui.activities.**
-keep class com.example.cnccalc.ui.viewmodels.**
-keep class com.example.cnccalc.data.repository.**
-keep class com.example.cnccalc.data.local.dao.**

# Сохраняем ваши модели данных
-keep class com.example.cnccalc.data.model.** {
    <fields>;
    <methods>;
}

# ------------------------------
# Общие правила для сериализации
# ------------------------------
# Сохраняем классы с аннотациями сериализации
-keepclasseswithmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}