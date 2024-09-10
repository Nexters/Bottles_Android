plugins {
    id("team.bottles.android.library.compose")
    id("team.bottles.android.library")
    id("team.bottles.android.feature")
    id("team.bottles.android.hilt")
    id("team.bottles.kotlin.serialization")
}

android {
    namespace = "com.team.bottles.feat.mypage"

    defaultConfig {
        buildConfigField("String", "VERSION_NAME", "\"${libs.versions.versionName.get()}\"")
        buildConfigField("Integer", "VERSION_CODE", libs.versions.versionCode.get())
    }
}

dependencies {

}