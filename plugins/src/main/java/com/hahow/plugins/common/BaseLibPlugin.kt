@file:Suppress("UnstableApiUsage")

package com.hahow.plugins.common

import com.android.build.gradle.LibraryExtension
import com.hahow.plugins.extension.configureAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.the
import org.jetbrains.kotlin.konan.properties.Properties
import kotlin.properties.Delegates

/**
 * 自定義Module gradle plugin
 */
class BaseLibPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.android")
                apply("quality.ktlint")
                apply("de.mannodermaus.android-junit5")
            }

            extensions.configure<LibraryExtension> {
                flavorDimensions += listOf("default")

                configureAndroid()

                buildTypes {
                    release {
                        isMinifyEnabled = true
                        proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
                    }

                    debug {

                    }
                }
            }

            val libs = the<org.gradle.accessors.dm.LibrariesForLibs>()
            dependencies {
                "implementation"(libs.bundles.androidx)
                "implementation"(libs.androidx.appcompat)
                "implementation"(libs.koin)

                "implementation"(libs.coroutines)

                //region Test
                "testImplementation"(libs.junit)
                "androidTestImplementation"(libs.androidx.junit)
                "androidTestImplementation"(libs.androidx.espresso.core)
                "testImplementation"(libs.bundles.test.koin)

                "testImplementation"(libs.test.coroutines)

                // (Required) Writing and executing Unit Tests on the JUnit Platform
                "testImplementation"(libs.junit.jupiter.api)
                "testRuntimeOnly"(libs.junit.jupiter.engine)

                // (Optional) If you need "Parameterized Tests"
                "testImplementation"(libs.junit.jupiter.params)

                // (Optional) If you also have JUnit 4-based tests
                "testImplementation"(libs.junit)
                "testRuntimeOnly"(libs.junit.vintage.engine)
                //endregion
            }
        }
    }
}