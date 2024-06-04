package com.hahow.plugins.extension

import org.gradle.api.Project
import com.android.build.gradle.BaseExtension
import com.hahow.plugins.configs.Version
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.the

@Suppress("UnstableApiUsage")
internal fun Project.configureCompose(commonExtension: BaseExtension){
    commonExtension.apply {
        buildFeatures.compose = true
        composeOptions.kotlinCompilerExtensionVersion = Version.KOTLIN_COMPILER_EXTENSION

        val libs = the<org.gradle.accessors.dm.LibrariesForLibs>()
        dependencies {
            "implementation"(libs.bundles.compose)
            "implementation"(libs.koin.compose)
            "implementation"(platform(libs.compose.bom))
            "implementation" (libs.compose.constraintlayout)
            "androidTestImplementation"(platform(libs.compose.bom))
            "androidTestImplementation"(libs.androidx.compose.ui.test)
        }
    }
}