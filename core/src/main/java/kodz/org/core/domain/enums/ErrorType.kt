package kodz.org.core.domain.enums

import com.google.gson.annotations.SerializedName


/**
 * Created by Murat Yüksektepe on 9.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
enum class ErrorType(val type: String) {
    @SerializedName("blocker")
    BLOCKER("blocker"),

    @SerializedName("warning")
    WARNING("warning");

    companion object {
        infix fun from(type: String): ErrorType? = ErrorType.values().firstOrNull { it.type == type }
    }
}