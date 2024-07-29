import com.team.bottles.conventions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            dependencies {
                add("implementation", project(":core:design-system"))
                add("implementation", project(":core:ui"))
                add("implementation", project(":core:domain"))
                add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())
            }
        }
    }
}