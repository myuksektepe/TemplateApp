package kodz.org.core.model

import com.google.gson.annotations.SerializedName

data class ClickEventModel(
    @SerializedName("eventTypeCode") val eventTypeCode: String? = null,
    @SerializedName("endpoint") val endpoint: String? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("dialogBox") val dialogBox: String? = null,
)