
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.dete.kt)
    alias(libs.plugins.compose.compiler) apply false

    alias(libs.plugins.vaniktech.publish)
    id("maven-publish")
}