@file:Suppress("UnstableApiUsage")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    includeBuild("build-logic")

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://devrepo.kakao.com/nexus/content/groups/public/")
    }
}

gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:testClasses"))

rootProject.name = "Bottles"

include(":app")

include(":core:design-system")
include(":core:data")
include(":core:domain")
include(":core:network")
include(":core:datastore")
include(":core:ui")
include(":core:navigator")
include(":core:common")
include(":core:local")
include(":core:exception")

include(":feat:recommendation")
include(":feat:sandbeach")
include(":feat:login")
include(":feat:mypage")
include(":feat:onboarding")
include(":feat:profile")
include(":feat:ping-pong")
include(":feat:splash")
include(":feat:report")
include(":feat:setting")
include(":feat:like")