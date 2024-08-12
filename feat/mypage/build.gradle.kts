import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id("team.bottles.android.library.compose")
    id("team.bottles.android.library")
    id("team.bottles.android.feature")
    id("team.bottles.android.hilt")
    id("team.bottles.kotlin.serialization")
}

android {
    namespace = "com.team.bottles.feat.mypage"

    buildTypes {
        val debugUrl = "BOTTLES_MY_PAGE_URL"
        val properties = Properties().apply { load(rootProject.file("local.properties").inputStream()) }

        getByName("release") {
            buildConfigField(
                "String",
                debugUrl, // TODO : 릴리즈 용 URL 생성 가능성 있음
                properties.getProperty(debugUrl) // // TODO : 릴리즈 용 URL 생성 가능성 있음
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

}