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

    implementation(projects.feat.bottle)
    implementation(projects.feat.sandbeach)
    implementation(projects.feat.login)
    implementation(projects.feat.mypage)
    implementation(projects.feat.onboarding)
    implementation(projects.feat.profile)

    // Compose
    implementation(libs.androidx.compose.activity)

    implementation(libs.androidx.navigation.compose)
    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}