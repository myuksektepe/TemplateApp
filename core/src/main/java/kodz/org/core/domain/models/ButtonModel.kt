package kodz.org.core.domain.models

import com.google.gson.annotations.SerializedName
import kodz.org.core.domain.enums.ButtonType
import kodz.org.core.domain.enums.CommonIcons
import kodz.org.core.domain.enums.EventTypeCode
import kodz.org.core.domain.enums.TextWeightType

data class ButtonModel(
    @SerializedName("type") val type: ButtonType? = null,
    @SerializedName("icon") val icon: CommonIcons? = null,
    @SerializedName("text") val text: String? = null,
    @SerializedName("textColor") val textColor: String? = null,
    @SerializedName("textWeight") val textWeightType: TextWeightType? = null,
    @SerializedName("backgroundColor") val backgroundColor: String? = null,
    @SerializedName("showUnderline") val showUnderline: Boolean? = false,
    @SerializedName("eventType") val eventType: EventTypeCode? = null,
)