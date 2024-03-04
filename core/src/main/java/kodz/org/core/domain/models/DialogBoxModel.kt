package kodz.org.core.domain.models

import com.google.gson.annotations.SerializedName

class DialogBoxModel(
    @SerializedName("showOnce") val showOnce: Boolean? = null,
    @SerializedName("tag") val tag: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("lottie") val lottie: LottieModel? = null,
    @SerializedName("primaryButton") val primaryButton: ButtonModel? = null,
    @SerializedName("secondaryButton") val secondaryButton: ButtonModel? = null,
) {
    class ViewEntity(
        val showOnce: Boolean? = null,
        val tag: String? = null,
        val title: TextModel? = null,
        val description: TextModel? = null,
        val lottie: LottieModel? = null,
        val primaryButton: ButtonModel? = null,
        val secondaryButton: ButtonModel? = null
    )

    fun toViewEntity() = ViewEntity(
        showOnce = this.showOnce,
        tag = this.tag,
        title = TextModel.DynamicText(this.title),
        description = TextModel.DynamicText(this.description),
        lottie = this.lottie,
        primaryButton = this.primaryButton,
        secondaryButton = this.secondaryButton
    )
}