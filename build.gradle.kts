import com.vanniktech.maven.publish.SonatypeHost

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.dete.kt)
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.vaniktech.publish)
    id("maven-publish")
}

mavenPublishing {

    coordinates("io.github.dinoy-raj", "droid-time", "1.0.0")

    pom {
        name.set("Droid Time")
        description.set("Relative and absolute date time formatting android library generates message corresponding to system default locale")
        inceptionYear.set("2024")
        url.set("https://github.com/dinoy-raj/droidTime")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("dinoy-raj")
                name.set("Dinoy Raj")
                url.set("https://github.com/dinoy-raj")
            }
        }
        scm {
            url.set("https://github.com/dinoy-raj/droidTime")
        }
    }

    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL, automaticRelease = true)
    signAllPublications()
}