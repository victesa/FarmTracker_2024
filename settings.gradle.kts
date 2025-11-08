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

rootProject.name = "FarmTracker"
include(":app")
include(":core")
include(":core:database")
include(":features")
include(":features:home")
include(":core:ui")
include(":features:authentication")
include(":core:designsystem")
include(":core:datastore")
include(":features:profile")
include(":features:animals")
include(":features:production")
include(":features:addInfo")
include(":features:finance")
