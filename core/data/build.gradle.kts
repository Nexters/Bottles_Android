plugins {
    id("team.bottles.android.library")
    id("team.bottles.android.hilt")
}

android {
    namespace = "com.team.bottles.data"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.network)
}