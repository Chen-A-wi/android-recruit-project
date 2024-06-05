plugins {
    alias(libs.plugins.android.library)
    id(libs.plugins.base.library.get().pluginId)
    kotlin("plugin.serialization") version "1.9.23"
}

android {
    namespace = "com.hahow.data"
}

dependencies {
    implementation(libs.retrofit2)
    implementation(libs.kotlinx.serialization.json)
    implementation(project(":common"))
    implementation(project(":network"))
    implementation(project(":domain"))
}