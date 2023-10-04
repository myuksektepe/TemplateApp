package kodz.org.core.model.screen

import com.google.gson.annotations.SerializedName

data class ClickEventModel(
    @SerializedName("eventTypeCode") val eventTypeCode: EventTypeCode? = null,
    @SerializedName("endpoint") val endpoint: String? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("dialogBox") val dialogBox: String? = null,
)