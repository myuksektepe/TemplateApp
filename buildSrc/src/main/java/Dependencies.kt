/**
 * Created by Murat Yüksektepe on 18.12.2022.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */

object DependencyVersions {
    const val kotlin = "1.9.0"
    const val appCompat = "1.6.1"
    const val material = "1.9.0"
    const val constraintlayout = "2.1.4"
    const val legacySupportV4 = "1.0.0"
    const val recyclerView = "1.2.1"
    const val recyclerViewSelection = "1.2.0-alpha01"
    const val swipeRefreshLayout = "1.2.0-alpha01"
    const val viewPager2 = "1.0.0"
    const val flexBox = "3.0.0"

    // Kotlin
    const val kotlinReflec = "1.9.10"

    // KTX
    const val ktxCore = "1.12.0"
    const val ktxActivity = "1.7.2"
    const val ktxFragment = "1.6.1"
    const val ktxLifecycle = "2.6.0-alpha03"

    // Hilt
    const val hilt = "2.43.2"
    const val hiltCompiler = "1.0.0-alpha01"

    // Coroutines
    const val coroutines = "1.6.4"

    // Lifecycle
    const val lifecycle = "2.5.1"

    // Navigation
    const val navigation = "2.5.3"

    // Ktor
    const val ktor = "2.2.1"

    // Retrofit
    const val retrofit = "2.9.0"
    const val retrofitCoroutinesAdapter = "0.9.2"

    // OkHttp
    const val okHttpLogging = "4.10.0"

    // Glide
    const val glide = "4.14.2"

    // Auto Image Slider
    const val autoImageSlider = "1.4.0"

    // Shimmer
    const val shimmer = "0.5.0"

    // SDP
    const val sdp = "1.1.0"

    // Lottie
    const val lottie = "6.0.0"

    // Blurry
    const val blurry = "4.0.1"

    // GSON
    const val gson = "2.10.1"

    // Test
    const val junitTest = "4.13.2"
    const val junit = "1.1.4"
    const val espresso = "3.5.1"
}

object Dependencies {

    //
    const val appcompat = "androidx.appcompat:appcompat:${DependencyVersions.appCompat}"
    const val material = "com.google.android.material:material:${DependencyVersions.material}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${DependencyVersions.constraintlayout}"
    const val legacySupportV4 = "androidx.legacy:legacy-support-v4:${DependencyVersions.legacySupportV4}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${DependencyVersions.recyclerView}"
    const val recyclerViewSelection = "androidx.recyclerview:recyclerview-selection:${DependencyVersions.recyclerViewSelection}"
    const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${DependencyVersions.swipeRefreshLayout}"
    const val flexBox = "com.google.android.flexbox:flexbox:${DependencyVersions.flexBox}"

    // Kotlin
    const val kotlinReflec = "org.jetbrains.kotlin:kotlin-reflect:${DependencyVersions.kotlinReflec}"

    // KTX
    const val ktxCore = "androidx.core:core-ktx:${DependencyVersions.ktxCore}"
    const val ktxActivity = "androidx.activity:activity-ktx:${DependencyVersions.ktxActivity}"
    const val ktxFragment = "androidx.fragment:fragment-ktx:${DependencyVersions.ktxFragment}"
    const val ktxLifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${DependencyVersions.lifecycle}"
    const val ktxLifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${DependencyVersions.lifecycle}"
    const val ktxLifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${DependencyVersions.lifecycle}"
    const val ktxNavigationUi = "androidx.navigation:navigation-ui-ktx:${DependencyVersions.navigation}"
    const val ktxNavigationFragment = "androidx.navigation:navigation-fragment-ktx:${DependencyVersions.navigation}"

    // Hilt
    const val hilt = "com.google.dagger:hilt-android:${DependencyVersions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${DependencyVersions.hilt}"
    const val hiltCompilerX = "androidx.hilt:hilt-compiler:${DependencyVersions.hiltCompiler}"

    // Coroutines
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${DependencyVersions.coroutines}"

    // Lifecycle
    const val lifecycleViewModelSavedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${DependencyVersions.lifecycle}"
    const val lifecycleCommonJava8 = "androidx.lifecycle:lifecycle-common-java8:${DependencyVersions.lifecycle}"

    // Ktor
    const val ktorCore = "io.ktor:ktor-client-core:${DependencyVersions.ktor}"
    const val ktorCio = "io.ktor:ktor-client-cio:${DependencyVersions.ktor}"
    const val ktorContentNegotiation = "io.ktor:ktor-client-content-negotiation:${DependencyVersions.ktor}"
    const val ktorJson = "io.ktor:ktor-serialization-kotlinx-json:${DependencyVersions.ktor}"
    const val ktorLogging = "io.ktor:ktor-client-logging:${DependencyVersions.ktor}"

    // Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${DependencyVersions.retrofit}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${DependencyVersions.retrofit}"
    const val retrofitCoroutinesAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${DependencyVersions.retrofitCoroutinesAdapter}"

    // OkHttp
    const val okHttpLogging = "com.squareup.okhttp3:logging-interceptor:${DependencyVersions.okHttpLogging}"

    // Glide
    const val glide = "com.github.bumptech.glide:glide:${DependencyVersions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${DependencyVersions.glide}"

    // Auto Image Slider
    const val autoImageSlider = "com.github.smarteist:autoimageslider:${DependencyVersions.autoImageSlider}"

    // Shimmer
    const val shimmer = "com.facebook.shimmer:shimmer:${DependencyVersions.shimmer}"

    // ViewPager2
    const val viewPager2 = "androidx.viewpager2:viewpager2:${DependencyVersions.viewPager2}"

    // SDP
    const val sdp = "com.intuit.sdp:sdp-android:${DependencyVersions.sdp}"

    // Lottie
    const val lottie = "com.airbnb.android:lottie:${DependencyVersions.lottie}"

    // Blurry
    const val blurry = "jp.wasabeef:blurry:${DependencyVersions.blurry}"

    // GSON
    const val gson = "com.google.code.gson:gson:${DependencyVersions.gson}"

    // Test
    const val junitTest = "junit:junit:${DependencyVersions.junitTest}"
    const val junit = "androidx.test.ext:junit:${DependencyVersions.junit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${DependencyVersions.espresso}"
}