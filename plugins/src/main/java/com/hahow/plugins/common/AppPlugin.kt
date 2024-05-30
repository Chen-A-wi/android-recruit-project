@file:Suppress("UnstableApiUsage")

package com.hahow.plugins.common

import com.android.build.api.dsl.ApplicationExtension
import com.hahow.plugins.configs.Version
import com.hahow.plugins.extension.configureAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.the

class AppPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = the<org.gradle.accessors.dm.LibrariesForLibs>()

            with(pluginManager) {
                apply("org.jetbrains.kotlin.android")
                apply("quality.ktlint")
            }

            extensions.configure<ApplicationExtension> {
                defaultConfig {
                    targetSdk = Version.TARGET_SDK
                    versionCode = Version.VERSION_CODE
                    versionName = Version.VERSION_NAME

                    vectorDrawables {
                        useSupportLibrary = true
                    }
                }

                configureAndroid()

                buildTypes {
                    release {
                        isMinifyEnabled = true
                        proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
                    }

                    debug {

                    }
                }

                packaging {
                    resources.excludes.apply {
                        add("META-INF/AL2.0")
                        add("META-INF/LGPL2.1")
                    }
                }
            }

            dependencies {
                "implementation"(libs.bundles.androidx)
                "implementation"(platform(libs.kotlin.bom))
                "implementation"(libs.koin)

                "testImplementation"(libs.bundles.test.koin)
                "testImplementation"(libs.junit.jupiter.api)
                "testRuntimeOnly"(libs.bundles.test.runtime.only)
                "androidTestImplementation"(libs.bundles.android.test)
                "debugImplementation"(libs.bundles.debug)
            }
        }
    }
}