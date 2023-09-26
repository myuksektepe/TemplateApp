package kodz.org.core.base.data.http

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


/**
 * Created by Murat Yüksektepe on 14.01.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
val gson = Gson()

//convert a data class to a map
fun <T> T.serializeToMap(): Map<String, String> {
    return convert()
}

//convert a map to a data class
inline fun <reified T> Map<String, String>.toDataClass(): T {
    return convert()
}

//convert an object of type I to type O
inline fun <I, reified O> I.convert(): O {
    val json = gson.toJson(this)
    return gson.fromJson(json, object : TypeToken<O>() {}.type)
}

inline fun <reified O> String.toResponseModel(): O? {
    return try {
        gson.fromJson(this, object : TypeToken<O>() {}.type)
    } catch (e: Exception) {
        Log.e("applog", "${e.message}")
        null
    }
}