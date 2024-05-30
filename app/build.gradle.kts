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
//    implementation(libs.retrofit2)
//    implementation(project(":common"))
//    implementation(project(":data"))
//    implementation(project(":domain"))
//    implementation(project(":network"))

    //region Compose destinations
    ksp(libs.compose.destinations.ksp)
    implementation(libs.compose.destinations.core)
    implementation(libs.compose.destinations.bottom.sheet)
    //endregion

    implementation(libs.bundles.paging)
    testImplementation(libs.test.paging.common)
}