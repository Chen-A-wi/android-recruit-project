plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
}

gradlePlugin {
    plugins {
        create("AppPlugin") {
            id = "plugins.app"
            implementationClass = "com.hahow.plugins.common.AppPlugin"
        }
        create("BaseLibPlugin") {
            id = "plugins.base-lib"
            implementationClass = "com.hahow.plugins.common.BaseLibPlugin"
        }
        create("ComposePlugin") {
            id = "plugins.compose"
            implementationClass = "com.hahow.plugins.common.ComposePlugin"
        }
        create("Ktlint") {
            id = "quality.ktlint"
            implementationClass = "com.hahow.plugins.quality.KtlintPlugin"
        }
    }
}

dependencies {
    implementation(libs.gradle.tools.build)
    implementation(libs.kotlin.gradle.plugin)
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}