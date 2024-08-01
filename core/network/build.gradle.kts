import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id("team.bottles.android.library")
    id("team.bottles.android.hilt")
    id("team.bottles.kotlin.serialization")
}

android {
    namespace = "com.team.bottles.network"

    buildTypes {
        val debugUrl = "BOTTLES_DEBUG_BASE_URL"
        val properties = Properties().apply { load(rootProject.file("local.properties").inputStream()) }

        getByName("release") {
            buildConfigField(
                "String",
                debugUrl, // TODO : 릴리즈 서버 생성 후 수정
                properties.getProperty(debugUrl) // // TODO : 릴리즈 서버 생성 후 수정
            )
        }
        getByName("debug") {
            buildConfigField(
                "String",
                debugUrl,
                properties.getProperty(debugUrl)
            )
        }
    }
}

dependencies {
    implementation(projects.core.datastore)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
}