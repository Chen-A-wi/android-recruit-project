plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_19
    targetCompatibility = JavaVersion.VERSION_19
}