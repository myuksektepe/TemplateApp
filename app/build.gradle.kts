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

    // Main
    implementation(Dependencies.appcompat)
    implementation(Dependencies.material)
    implementation(Dependencies.sdp)
    implementation(Dependencies.constraintlayout)

    // KTX
    implementation(Dependencies.ktxCore)
    implementation(Dependencies.ktxActivity)
    implementation(Dependencies.ktxFragment)
    implementation(Dependencies.ktxNavigationUi)
    implementation(Dependencies.ktxNavigationFragment)
    implementation(Dependencies.ktxLifecycleViewModel)

    // Hilt
    implementation(Dependencies.hilt)
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    kapt(Dependencies.hiltCompiler)
    kapt(Dependencies.hiltCompilerX)

    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.10")

    // Test
    testImplementation(Dependencies.junitTest)
    androidTestImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.espressoCore)
}