plugins {
    id("team.bottles.android.library")
    id("team.bottles.android.library.compose")
}

android {
    namespace = "com.team.bottles.core.designsystem"
}

dependencies {
    // Compose
    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.ui.tooling)
    api(libs.androidx.compose.ui.graphics)
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.lifecycle)

    api(libs.coil.compose)
}