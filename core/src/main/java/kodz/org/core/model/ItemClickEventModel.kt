package kodz.org.core.model

import com.google.gson.annotations.SerializedName

data class ItemClickEventModel(
    @SerializedName("eventTypeCode") val eventTypeCode: EventTypeCode? = null,
    @SerializedName("endpoint") val endpoint: String? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("dialogBox") val dialogBox: DialogBox? = null,
)