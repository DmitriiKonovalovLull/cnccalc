pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://chaquo.com/maven-test") }  // ← ИСПРАВЛЕННЫЙ URL
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://chaquo.com/maven-test") }  // ← ИСПРАВЛЕННЫЙ URL
    }
}

rootProject.name = "cnccalc"
include(":app")