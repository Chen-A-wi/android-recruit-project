package com.hahow.plugins.common

import com.android.build.gradle.BaseExtension
import com.hahow.plugins.extension.configureCompose
import org.gradle.api.Plugin
import org.gradle.kotlin.dsl.getByType
import org.gradle.api.Project

/**
 * 自定義Compose gradle plugin
 */
class ComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configureCompose(extensions.getByType<BaseExtension>())
        }
    }
}