package kodz.org.core.model

import com.google.gson.annotations.SerializedName


/**
 * Created by Murat Yüksektepe on 9.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
enum class ButtonType(val type: String) {
    @SerializedName("text")
    TEXT("text"),

    @SerializedName("rounded")
    ROUNDED("rounded");

    companion object {
        infix fun from(type: String): ButtonType? = ButtonType.values().firstOrNull { it.type == type }
    }
}