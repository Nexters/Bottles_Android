plugins {
    `kotlin-dsl`
}

group = "com.team.bottles.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.agp)
    compileOnly(libs.kotlin.gradleplugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "team.bottles.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = "team.bottles.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "team.bottles.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "team.bottles.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = "team.bottles.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidHilt") {
            id = "team.bottles.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("kotlinSerialization") {
            id = "team.bottles.kotlin.serialization"
            implementationClass = "KotlinSerializationConventionPlugin"
        }
        register("androidFirebase") {
            id = "team.bottles.android.firebase"
            implementationClass = "AndroidFirebaseConventionPlugin"
        }
    }
}