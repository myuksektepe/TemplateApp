package kodz.org.core.domain.models

import com.google.gson.annotations.SerializedName
import kodz.org.core.domain.enums.EventTypeCode

data class ClickEventModel(
    @SerializedName("eventTypeCode") val eventTypeCode: EventTypeCode? = null,
    @SerializedName("endpoint") val endpoint: String? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("dialogBoxModel") val dialogBoxModel: DialogBoxModel? = null,
)