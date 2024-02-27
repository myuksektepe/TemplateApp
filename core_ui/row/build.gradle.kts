plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "kodz.org.core_ui.row"
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
    buildFeatures {
        dataBinding = true
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
        kotlinOptions {
            jvmTarget = "18"
        }
    }

    composeOptions {
        kotlinCompilerExtensionVersion = DependencyVersions.kotlinCompilerExtensionVersion
    }
}

dependencies {
    kapt("androidx.annotation:annotation:1.7.1")

    // Module
    implementation(project(":core"))
    implementation(project(":core_ui:component"))

    // Main
    implementation(Dependencies.appcompat)
    implementation(Dependencies.material)
    implementation(Dependencies.sdp)

    // Kotlin
    implementation(Dependencies.kotlinReflec)

    // KTX
    implementation(Dependencies.ktxCore)
    implementation(Dependencies.ktxFragment)

    // Compose
    implementation(Dependencies.composeUi)
    implementation(platform(Dependencies.composeBom))
    androidTestImplementation(Dependencies.composeBom)
    implementation(Dependencies.composeMaterial3)

    // Gson
    implementation(Dependencies.gson)

    // Glide
    implementation(Dependencies.glide) {
        exclude(group = "com.android.support")
    }
    kapt(Dependencies.glideCompiler)
    implementation(Dependencies.okHttp)
    implementation(Dependencies.glideOkHttpIntegration) {
        exclude("glide-parent")
    }

    // Lottie
    implementation(Dependencies.lottie)

    // ViewPager
    implementation(Dependencies.viewPager2)

    // Hilt
    implementation(Dependencies.hilt)
    kapt(Dependencies.hiltCompiler)
    kapt(Dependencies.hiltCompilerX)

    // Test
    testImplementation(Dependencies.junitTest)
    androidTestImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.espressoCore)
}