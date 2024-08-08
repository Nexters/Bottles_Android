import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("team.bottles.android.application")
    id("team.bottles.android.application.compose")
    id("team.bottles.android.hilt")
    id("team.bottles.kotlin.serialization")
}

android {
    namespace = "com.team.bottles"

    defaultConfig {
        applicationId = "com.team.bottles"
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val kakaoNativeAppKey = gradleLocalProperties(rootDir, providers).getProperty("KAKAO_NATIVE_APP_KEY")
        buildConfigField("String", "KAKAO_NATIVE_APP_KEY", kakaoNativeAppKey)

        manifestPlaceholders["KAKAO_NATIVE_APP_KEY"] = kakaoNativeAppKey.replace("\"","")
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    hilt {
        enableAggregatingTask = true
    }
}

dependencies {
    implementation(projects.core.designSystem)
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.core.datastore)
    implementation(projects.core.ui)
    implementation(projects.core.navigator)
    implementation(projects.core.common)

    implementation(projects.feat.bottle)
    implementation(projects.feat.sandbeach)
    implementation(projects.feat.login)
    implementation(projects.feat.mypage)
    implementation(projects.feat.onboarding)
    implementation(projects.feat.profile)
    implementation(projects.feat.signup)
    implementation(projects.feat.pingPong)

    // Compose
    implementation(libs.androidx.compose.activity)
    implementation(libs.androidx.navigation.compose)

    // Kakao
    implementation(libs.kakao.login)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}