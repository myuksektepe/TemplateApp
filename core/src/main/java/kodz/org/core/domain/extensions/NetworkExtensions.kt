package kodz.org.core.domain.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import kodz.org.core.common.AppLog
import java.net.HttpURLConnection
import java.net.URL


/**
 * Created by Murat Yüksektepe on 31.12.2022.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */

fun Context.isOnline(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val n = cm.activeNetwork
        if (n != null) {
            val nc = cm.getNetworkCapabilities(n)
            //It will check for both wifi and cellular network
            return nc!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(
                NetworkCapabilities.TRANSPORT_WIFI
            )
        }
        return false
    } else {
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}

fun String.isUrlReachable(): Boolean {
    try {
        val url = URL(this)
        val httpURLConnection = url.openConnection() as HttpURLConnection
        return httpURLConnection.responseCode == 200
    } catch (e: Exception) {
        AppLog(e.toString())
        // throw UnknownHostException(e.message)
    }
    return false
}