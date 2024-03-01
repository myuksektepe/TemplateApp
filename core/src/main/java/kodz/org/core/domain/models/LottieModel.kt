package kodz.org.core.domain.models

import com.google.gson.annotations.SerializedName
import kodz.org.core.domain.enums.CommonLottie

data class LottieModel(
    @SerializedName("name") val name: CommonLottie? = null,
    @SerializedName("autoPlay") val autoPlay: Boolean? = null,
    @SerializedName("loop") val loop: Boolean? = null,
    @SerializedName("repeatCount") val repeatCount: Int? = null
)