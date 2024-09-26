plugins {
    id("team.bottles.android.library")
}

android {
    namespace = "com.team.bottles.core.common"
}

dependencies {
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)
}