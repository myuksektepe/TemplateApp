package kodz.org.core.extension

import android.graphics.Color
import java.util.Locale
import java.util.concurrent.TimeUnit


/**
 * Created by Murat Yüksektepe on 4.01.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */

fun Any?.ifNullOrEmpty(default: String) =
    if ((this == null) || ((this is CharSequence) && this.isEmpty()))
        default
    else
        this.toString()

fun String?.takeIfNullOrEmpty(): String? {
    val s = this.takeIf {
        !it.isNullOrEmpty()
    }
    return s
}

fun Long?.getDurationText(): String {
    this?.let {
        val min = TimeUnit.MILLISECONDS.toMinutes(this)
        val sec = TimeUnit.MILLISECONDS.toSeconds(this) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(this))
        return String.format(Locale.US, "%02d:%02d", min, sec)
    } ?: kotlin.run {
        return "NaN"
    }
}


fun String?.toColor(): Int? {
    return try {
        Color.parseColor(this)
    } catch (e: Exception) {
        null
    }
}

fun String?.toColorLong(): Long? {
    return try {
        this?.replace("#", "0xFF")?.toLong()
    } catch (e: Exception) {
        null
    }
}