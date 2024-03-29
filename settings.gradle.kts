pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

rootProject.name = "Farm Tracker"
include(":app")
include(":core:ui")
include(":core:ui")
include(":core:ui")
include(":core:designsystem")
include(":features:authentication")
include(":processor")
include(":features:profile")
include(":features:home")
include(":core:database")
