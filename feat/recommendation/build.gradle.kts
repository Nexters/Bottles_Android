import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id("team.bottles.android.library")
    id("team.bottles.android.library.compose")
    id("team.bottles.android.feature")
    id("team.bottles.android.hilt")
    id("team.bottles.kotlin.serialization")
}

android {
    namespace = "com.team.bottles.feat.recommendation"

    defaultConfig {
        val url = "BOTTLES_RECOMMENDATIONS_URL"
        val properties = Properties().apply { load(rootProject.file("local.properties").inputStream()) }

        buildConfigField(
            "String",
            url,
            properties.getProperty(url)
        )

        buildConfigField(
            "String",
            "DEVICE",
            properties.getProperty("DEVICE")
        )

        buildConfigField(
            "String",
            "APP_VERSION",
            "\"${libs.versions.appVersion.get()}\""
        )
    }
}

dependencies {

    implementation(project(":feat:profile"))
}