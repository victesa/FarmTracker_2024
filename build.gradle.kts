plugins {
    id("com.google.gms.google-services") version "4.4.0" apply false
    id("com.google.devtools.ksp") version "1.9.0-1.0.13" apply false
    //id("com.android.library") version "8.2.0" apply false
    //id("org.jetbrains.kotlin.android") version "1.9.20" apply false
    //id("com.android.application") version "8.2.0" apply false
    //id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    //id("com.android.library") version "8.1.0" apply false
}

buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48")
    }
}

