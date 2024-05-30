plugins {
    id("com.android.application")
    id("plugins.app")
    id("plugins.compose")
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.jetbrains.kotlin)
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
dependencies {
    implementation(libs.bundles.material)
//    implementation(project(":common"))
//    implementation(project(":data"))
//    implementation(project(":domain"))
//    implementation(project(":network"))

    implementation(libs.bundles.paging)
    testImplementation(libs.test.paging.common)
}
