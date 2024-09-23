import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id("team.bottles.android.library.compose")
    id("team.bottles.android.library")
    id("team.bottles.android.feature")
    id("team.bottles.android.hilt")
    id("team.bottles.kotlin.serialization")
}

android {
    namespace = "com.team.bottles.feat.profile"

    defaultConfig {
        val urls = listOf("BOTTLES_CREATE_PROFILE_URL", "BOTTLES_PROFILE_EDIT_URL")
        val properties = Properties().apply { load(rootProject.file("local.properties").inputStream()) }

        urls.forEach { url ->
            buildConfigField(
                "String",
                url,
                properties.getProperty(url)
            )
        }
    }
}

dependencies {

}