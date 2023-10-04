plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = ConfigData.appId
    compileSdk = ConfigData.compileSdk
    defaultConfig {
        applicationId = ConfigData.appId
        minSdk = ConfigData.minSdk
        targetSdk = ConfigData.targetSdk
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "$project.rootDir/tools/proguard-rules.pro"
            )
        }
    }
    buildFeatures { dataBinding = true }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
        kotlinOptions {
            jvmTarget = "18"
        }
    }
}

dependencies {
    // Modules
    implementation(project(":core"))
    implementation(project(":feature:dashboard"))
    implementation(project(":feature:new_screen"))

    // Main
    implementation(Dependencies.appcompat)
    implementation(Dependencies.material)
    implementation(Dependencies.sdp)
    implementation(Dependencies.constraintlayout)

    // Kotlin
    // implementation(Dependencies.kotlinReflec)

    // KTX
    implementation(Dependencies.ktxCore)
    implementation(Dependencies.ktxActivity)
    implementation(Dependencies.ktxFragment)
    implementation(Dependencies.ktxNavigationUi)
    implementation(Dependencies.ktxNavigationFragment)
    implementation(Dependencies.ktxLifecycleViewModel)

    // Hilt
    implementation(Dependencies.hilt)
    kapt(Dependencies.hiltCompiler)
    kapt(Dependencies.hiltCompilerX)

    // Test
    testImplementation(Dependencies.junitTest)
    androidTestImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.espressoCore)
}