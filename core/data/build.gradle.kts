plugins {
    id("team.bottles.android.library")
    id("team.bottles.android.hilt")
}

android {
    namespace = "com.team.bottles.core.data"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.network)
    implementation(projects.core.datastore)
    implementation(projects.core.local)

    implementation(libs.jakewharton.timber)
}