plugins {
    id("team.bottles.android.library")
    id("team.bottles.android.library.compose")
}

android {
    namespace = "com.team.bottles.core.ui"
}

dependencies {
    implementation(projects.core.designSystem)

    implementation(libs.kotlinx.collections.immutable)
}