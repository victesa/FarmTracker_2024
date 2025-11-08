import org.gradle.api.artifacts.dsl.DependencyHandler


object Dependencies{
    const val daggerHiltAndroid = "com.google.dagger:hilt-android:${Versions.daggerHiltAndroidVersion}"
    const val daggerKaptCompiler = "com.google.dagger:hilt-android-compiler:${Versions.kaptCompiler}"
    const val hiltCompilerDependency = "androidx.hilt:hilt-compiler:${Versions.hiltCompiler}"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigation}"

    const val composeMaterial3 = "androidx.compose.material3:material3:1.2.0-rc01"
    const val composeUiPreview = "androidx.compose.ui:ui-tooling-preview"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling"
    const val composeUiJUnit4Test = "androidx.compose.ui:ui-test-junit4"
    const val composeUiManifestTest = "androidx.compose.ui:ui-test-manifest"
    const val composeWindowClass = "androidx.compose.material3:material3-window-size-class"
    const val composeViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1"
    const val composeLiveData = "androidx.compose.runtime:runtime-livedata"
    const val composeUi = "androidx.compose.ui:ui"
    const val composeUiNavigation = "androidx.navigation:navigation-compose:2.7.7"
    const val composeFoundation = "androidx.compose.foundation:foundation:1.4.0"
    const val composeGraphics = "androidx.compose.ui:ui-graphics"
    const val composeBom = "androidx.compose:compose-bom:2024.01.00"

    const val statusBar = "com.google.accompanist:accompanist-systemuicontroller:0.27.0"

    const val firebaseAnalytics = "com.google.firebase:firebase-analytics"
    const val firebaseBoM = "com.google.firebase:firebase-bom:32.7.0"
    const val firebaseAuth = "com.google.firebase:firebase-auth"

    const val coil = "io.coil-kt:coil-compose:2.5.0"

    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"

}


fun DependencyHandler.daggerHilt(){
    implementation(Dependencies.daggerHiltAndroid)
    ksp(Dependencies.daggerKaptCompiler)
    implementation(Dependencies.hiltCompilerDependency)
    implementation(Dependencies.hiltNavigationCompose)
}

fun DependencyHandler.firebase(){
    implementation(platform(Dependencies.firebaseBoM))
    implementation(Dependencies.firebaseAnalytics)
    implementation(Dependencies.firebaseAuth)
}

fun DependencyHandler.room() {
    implementation(Dependencies.roomRuntime)
    implementation(Dependencies.roomKtx)
    ksp(Dependencies.roomCompiler)
}

fun DependencyHandler.statusBar(){
    implementation(Dependencies.statusBar)
}

fun DependencyHandler.coil(){
    implementation(Dependencies.coil)
}

fun DependencyHandler.compose() {
    implementation(Dependencies.composeUi)
    implementation(platform(Dependencies.composeBom))
    implementation(Dependencies.composeFoundation)
    implementation(Dependencies.composeGraphics)
    implementation(Dependencies.composeLiveData)
    implementation(Dependencies.composeMaterial3)
    androidTest(Dependencies.composeUiJUnit4Test)
    debugImplementation(Dependencies.composeUiManifestTest)
    implementation(Dependencies.composeUiPreview)
    implementation(Dependencies.composeUiTooling)
    implementation(Dependencies.composeViewModel)
    implementation(Dependencies.composeWindowClass)
    implementation(Dependencies.composeUiNavigation)
}
