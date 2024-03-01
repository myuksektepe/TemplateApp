package kodz.org.core.domain.models

import com.google.gson.annotations.SerializedName

data class DialogBoxModel(
    @SerializedName("showOnce") val showOnce: Boolean? = null,
    @SerializedName("tag") val tag: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("lottie") val lottie: LottieModel? = null,
    @SerializedName("primaryButton") val primaryButton: ButtonModel? = null,
    @SerializedName("secondaryButton") val secondaryButton: ButtonModel? = null,
)