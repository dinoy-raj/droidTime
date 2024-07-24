import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.dete.kt)
    alias(libs.plugins.vaniktech.publish)
    id("maven-publish")
}

android {
    namespace = "com.example.droid_time"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

detekt {
    toolVersion = "1.23.6"
    config.setFrom(file("../config/detekt/detekt.yml"))
    buildUponDefaultConfig = true
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.kotlinx.datetime)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

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

    publishToMavenCentral(SonatypeHost.DEFAULT, automaticRelease = true)
    signAllPublications()
}

task("testClasses"){}

