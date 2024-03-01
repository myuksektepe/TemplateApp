buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath(ClassPaths.hilt)
        classpath(ClassPaths.navigation_safe_args)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven {
            setUrl("https://jitpack.io")
        }
    }
}

plugins {
    id("com.android.application") version "8.2.2" apply false
    id("com.android.library") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.20" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.20" apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
    id("com.google.dagger.hilt.android") version "2.44" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}