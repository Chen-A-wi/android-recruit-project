plugins {
    alias(libs.plugins.android.library)
    id(libs.plugins.base.library.get().pluginId)
}

android {
    namespace = "com.hahow.network"
}

dependencies {
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit2)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.serialization.retrofit2)
    implementation(libs.test.mockwebserver)
    implementation(project(":common"))
}