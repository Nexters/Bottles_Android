package com.team.bottles.conventions

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

internal fun Project.configureAndroidCompose(commonExtension: CommonExtension<*, *, *, *, *, *>) {
    with(pluginManager) {
        apply("org.jetbrains.kotlin.plugin.compose")
    }

    commonExtension.apply {
        buildFeatures {
            compose = true
        }
    }
}
