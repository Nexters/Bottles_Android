import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.jetbrains.kotlin.konan.properties.Properties
import java.io.FileInputStream

plugins {
    id("team.bottles.android.application")
    id("team.bottles.android.application.compose")
    id("team.bottles.android.hilt")
    id("team.bottles.kotlin.serialization")
    id("team.bottles.android.firebase")
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

    signingConfigs {
        create("release") {
            Properties().apply {
                load(FileInputStream(rootProject.file("local.properties")))
                storeFile = rootProject.file(this["STORE_FILE"] as String)
                keyAlias = this["KEY_ALIAS"] as String
                keyPassword = this["KEY_PASSWORD"] as String
                storePassword = this["STORE_PASSWORD"] as String
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
}

dependencies {
    implementation(projects.core.designSystem)
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.core.ui)
    implementation(projects.core.navigator)
    implementation(projects.core.common)
    implementation(projects.core.exception)

    implementation(projects.feat.bottle)
    implementation(projects.feat.sandbeach)
    implementation(projects.feat.login)
    implementation(projects.feat.mypage)
    implementation(projects.feat.onboarding)
    implementation(projects.feat.profile)
    implementation(projects.feat.pingPong)
    implementation(projects.feat.splash)
    implementation(projects.feat.report)
    implementation(projects.feat.setting)
    implementation(projects.feat.like)

    // Compose
    implementation(libs.androidx.compose.activity)
    implementation(libs.androidx.navigation.compose)

    // Kakao
    implementation(libs.kakao.login)

    // Timber
    implementation(libs.jakewharton.timber)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}