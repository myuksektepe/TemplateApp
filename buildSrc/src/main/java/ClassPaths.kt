/**
 * Created by Murat Yüksektepe on 09.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */

object ClassPathVersions {
    const val hilt = "2.43.2"
    const val navigation_safe_args = "2.5.3"
}

object ClassPaths {
    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${ClassPathVersions.hilt}"
    const val navigation_safe_args = "androidx.navigation:navigation-safe-args-gradle-plugin:${ClassPathVersions.navigation_safe_args}"
}