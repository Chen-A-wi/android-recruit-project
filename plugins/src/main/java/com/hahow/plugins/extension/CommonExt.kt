package com.hahow.plugins.extension

import com.android.build.api.dsl.CommonExtension
import com.hahow.plugins.configs.Version
import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

internal fun CommonExtension<*, *, *, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}

@Suppress("UnstableApiUsage")
internal fun CommonExtension<*, *, *, *, *, *>.configureAndroid() {
    apply {
        defaultConfig {
            compileSdk = Version.COMPILE_SDK
            minSdk = Version.MIN_SDK

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        compileOptions {
            sourceCompatibility = Version.jdk
            targetCompatibility = Version.jdk
        }

        kotlinOptions {
            jvmTarget = "${Version.jdk}"
        }

        testOptions {
            unitTests.isIncludeAndroidResources = true
        }

        buildFeatures {
            buildConfig = true
        }
    }
}
