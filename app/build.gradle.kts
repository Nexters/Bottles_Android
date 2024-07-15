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

    // Compose
    implementation(libs.androidx.compose.activity)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}