pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://chaquo.com/maven") } // Критически важно для Chaquopy
    }

    plugins {
        id("com.android.application") version "8.13.1"
        id("org.jetbrains.kotlin.android") version "2.0.21"
        id("org.jetbrains.kotlin.plugin.compose") version "2.0.21"
        id("com.chaquo.python") version "13.0.0"
        id("com.google.devtools.ksp") version "2.0.21-1.0.25"
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://chaquo.com/maven") }
    }

    // Версии библиотек для всего проекта
    versionCatalogs {
        create("libs") {
            library("room-runtime", "androidx.room:room-runtime:2.6.1")
            library("room-ktx", "androidx.room:room-ktx:2.6.1")
            library("room-compiler", "androidx.room:room-compiler:2.6.1")
            library("compose-bom", "androidx.compose:compose-bom:2024.08.00")
        }
    }
}

rootProject.name = "cnccalc"
include(":app")