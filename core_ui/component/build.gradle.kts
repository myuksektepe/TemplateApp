plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "kodz.org.core_ui.component"
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
    // Module
    implementation(project(":core"))
    implementation(project(":core_ui:ui"))

    // Main
    implementation(Dependencies.appcompat)
    implementation(Dependencies.material)
    implementation(Dependencies.sdp)

    // KTX
    implementation(Dependencies.ktxCore)

    // Gson
    implementation(Dependencies.gson)

    // Glide
    implementation(Dependencies.glide) {
        exclude(group = "com.android.support")
    }
    kapt("androidx.annotation:annotation:1.7.0")
    kapt(Dependencies.glideCompiler)

    // Lottie
    implementation(Dependencies.lottie)

    // ViewPager
    implementation(Dependencies.viewPager2)

    // Test
    testImplementation(Dependencies.junitTest)
    androidTestImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.espressoCore)
}