plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "kodz.org.core_ui.ui"
    compileSdk = ConfigData.compileSdk
    defaultConfig {
        minSdk = ConfigData.minSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        multiDexEnabled = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "$project.rootDir/tools/proguard-rules.pro"
            )
        }
    }
    buildFeatures { compose = true }
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

    // KTX
    implementation(Dependencies.ktxCore)

    // Compose
    implementation(Dependencies.composeUi)
    implementation(platform(Dependencies.composeBom))
    androidTestImplementation(Dependencies.composeBom)
    implementation(Dependencies.composeMaterial3)
    implementation(Dependencies.composeUiText)

}