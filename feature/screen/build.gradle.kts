plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "kodz.org.feature.screen"
    compileSdk = ConfigData.compileSdk
    defaultConfig {
        minSdk = ConfigData.minSdk
        targetSdk = ConfigData.targetSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "$project.rootDir/tools/proguard-rules.pro")
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
    implementation(project(":core_ui:row"))
    implementation(project(":core_ui:component"))

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
    kapt(Dependencies.hiltCompiler)
    kapt(Dependencies.hiltCompilerX)

    // Gson
    implementation(Dependencies.gson)
    implementation(Dependencies.gson)

    // Swipe Refresh Layout
    implementation(Dependencies.swipeRefreshLayout)

    // Test
    testImplementation(Dependencies.junitTest)
    androidTestImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.espressoCore)
}