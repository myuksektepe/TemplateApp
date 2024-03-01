package kodz.org.core.domain.enums

import com.google.gson.annotations.SerializedName
import kodz.org.core.R


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 23.10.2023.
 */
enum class CommonLottie(val lottieName: String, val resourceId: Int) {
    @SerializedName("error")
    ERROR("error", R.raw.error),

    @SerializedName("warning")
    WARNING("warning", R.raw.warning),

    @SerializedName("success")
    SUCCESS("success", R.raw.success),

    @SerializedName("notFound", alternate = ["404"])
    NOT_FOUND("notFound", R.raw.not_found);

    companion object {
        fun from(value: String): CommonLottie? {
            return values().firstOrNull { it.lottieName == value }
        }
    }
}