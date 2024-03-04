package kodz.org.core.domain.models

import com.google.gson.annotations.SerializedName
import kodz.org.core.domain.enums.ButtonType
import kodz.org.core.domain.enums.CommonIcons
import kodz.org.core.domain.enums.EventTypeCode
import kodz.org.core.domain.enums.TextWeightType
import kodz.org.core.domain.extensions.toColor

class ButtonModel(
    @SerializedName("type") val type: ButtonType? = null,
    @SerializedName("icon") val icon: CommonIcons? = null,
    @SerializedName("text") val text: String? = null,
    @SerializedName("textColor") val textColor: String? = null,
    @SerializedName("textWeight") val textWeightType: TextWeightType? = null,
    @SerializedName("backgroundColor") val backgroundColor: String? = null,
    @SerializedName("showUnderline") val showUnderline: Boolean? = false,
    @SerializedName("eventType") val eventType: EventTypeCode? = null,
) {
    class ViewEntity(
        val type: ButtonType? = null,
        val icon: CommonIcons? = null,
        val text: TextModel? = null,
        val textColor: Int? = null,
        val textWeightType: TextWeightType? = null,
        val backgroundColor: Int? = null,
        val showUnderline: Boolean? = false,
        val eventType: EventTypeCode? = null
    )

    fun toViewEntity() = ViewEntity(
        type = this.type,
        icon = this.icon,
        text = TextModel.DynamicText(this.text),
        textColor = this.textColor.toColor(),
        textWeightType = this.textWeightType,
        backgroundColor = this.backgroundColor.toColor(),
        showUnderline = this.showUnderline,
        eventType = this.eventType
    )
}