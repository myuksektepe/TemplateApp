plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "kodz.org.core"
    compileSdk = ConfigData.compileSdk
    defaultConfig {
        minSdk = ConfigData.minSdk
        lint.targetSdk = ConfigData.targetSdk
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
    kapt("androidx.annotation:annotation:1.7.0")
    // Main
    implementation(Dependencies.appcompat)
    implementation(Dependencies.material)
    implementation(Dependencies.sdp)

    // KTX
    implementation(Dependencies.ktxCore)
    implementation(Dependencies.ktxNavigationFragment)
    implementation(Dependencies.ktxNavigationUi)

    // Kotlin
    implementation(Dependencies.kotlinReflec)

    // Glide
    implementation(Dependencies.glide) {
        exclude(group = "com.android.support")
    }
    kapt(Dependencies.glideCompiler)
    implementation(Dependencies.okHttp)
    implementation(Dependencies.glideOkHttpIntegration) {
        exclude("glide-parent")
    }
    
    // Retrofit2
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitGson)
    implementation(Dependencies.retrofitCoroutinesAdapter)
    implementation(Dependencies.okHttpLogging)

    // Gson
    implementation(Dependencies.gson)

    // Lottie
    implementation(Dependencies.lottie)

    // Shimmer
    implementation(Dependencies.shimmer)

    // Hilt
    implementation(Dependencies.hilt)
    kapt(Dependencies.hiltCompiler)
    kapt(Dependencies.hiltCompilerX)

    // RecyclerView
    implementation(Dependencies.recyclerView)
    implementation(Dependencies.recyclerViewSelection)

    // ViewPager
    implementation(Dependencies.viewPager2)

    // SDP
    implementation(Dependencies.sdp)

    // Test
    testImplementation(Dependencies.junitTest)
    androidTestImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.espressoCore)
}