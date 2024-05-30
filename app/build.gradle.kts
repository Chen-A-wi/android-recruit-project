plugins {
    id("com.android.application")
    id("plugins.app")
    id("plugins.compose")
    alias(libs.plugins.devtools.ksp)
}

android {
    namespace = "in.hahow.android_recruit_project"
    flavorDimensions += listOf("default")

    defaultConfig {
        applicationId = "in.hahow.android_recruit_project"
    }

    applicationVariants.all {
        addJavaSourceFoldersToModel(
            File(
                layout.buildDirectory.asFile.get(),
                "generated/ksp/$name/kotlin",
            ),
        )
    }
}
