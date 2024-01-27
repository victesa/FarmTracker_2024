import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

object Dependencies {


    const val composeMaterial = "androidx.compose.material3:material3:${Versions.composeMaterial3}"
    const val composeUi = "androidx.compose.ui:ui"
    const val composeFoundation = "androidx.compose.foundation:foundation:1.4.0"
    const val composeUiGraphics = "androidx.compose.ui:ui-graphics:${Versions.compose}"
    const val composeUiToolingDebugging = "androidx.compose.ui:ui-tooling"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val composeRuntime = "androidx.compose.runtime:runtime:${Versions.compose}"
    const val composeActivity = "androidx.activity:activity-compose:1.8.0"
    const val composeBom = "androidx.compose:compose-bom:2023.03.00"
    const val composeJunit4Test = "androidx.compose.ui:ui-test-junit4"
    const val composeDebug = "androidx.compose.ui:ui-test-manifest"
    const val composeFragmentNavigation = "androidx.navigation:navigation-fragment-ktx:${Versions.nav_version}"
    const val composeUiNavigation = "androidx.navigation:navigation-ui-ktx:${Versions.nav_version}"
    const val composeNavigation = "androidx.navigation:navigation-compose:${Versions.nav_version}"
    const val composeWindowClass = "androidx.compose.material3:material3-window-size-class:${Versions.windowClass}"
    const val composeViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.viewModel}"

    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hiltAgp = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"

    const val coil = "io.coil-kt:coil-compose:2.5.0"

    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"

    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"

    const val statusBar = "com.google.accompanist:accompanist-systemuicontroller:${Versions.statusBar}"

    const val firebaseAnalytics = "com.google.firebase:firebase-analytics"
    const val firebaseBoM = "com.google.firebase:firebase-bom:32.7.0"
    const val firebaseAuth = "com.google.firebase:firebase-auth"

    //Dagger - Hilt
    const val daggerHiltAndroid = "com.google.dagger:hilt-android:${Versions.daggerHiltAndroidVersion}"
    const val daggerKaptCompiler = "com.google.dagger:hilt-android-compiler:${Versions.kaptCompiler}"
    const val daggerViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.daggerViewModel}"
    const val hiltCompilerDependency = "androidx.hilt:hilt-compiler:${Versions.hiltCompiler}"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigation}"
}

fun DependencyHandler.daggerHilt(){
    implementation(Dependencies.daggerHiltAndroid)
    ksp(Dependencies.daggerKaptCompiler)
    implementation(Dependencies.daggerViewModel)
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

fun DependencyHandler.retrofit() {
    implementation(Dependencies.retrofit)
    implementation(Dependencies.moshiConverter)
    implementation(Dependencies.okHttp)
    implementation(Dependencies.okHttpLoggingInterceptor)
}

fun DependencyHandler.compose() {
    implementation(Dependencies.composeViewModel)
    implementation(Dependencies.composeNavigation)
    implementation(Dependencies.composeWindowClass)
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeFoundation)
    implementation(Dependencies.composeUiToolingDebugging)
    implementation(Dependencies.composeUiToolingPreview)
    implementation(Dependencies.composeRuntime)
    implementation(Dependencies.composeActivity)
    implementation(Dependencies.composeUiGraphics)
    implementation(platform(Dependencies.composeBom))
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.composeFragmentNavigation)
    implementation(Dependencies.composeUiNavigation)
    debugImplementation(Dependencies.composeDebug)
}

fun DependencyHandler.hilt() {
    implementation(Dependencies.hiltAndroid)
    ksp(Dependencies.hiltCompiler)
}

fun DependencyHandler.booksDataSource() {
    implementation(project(":books-datasource"))
}

fun DependencyHandler.booksUi() {
    implementation(project(":books-ui"))
}