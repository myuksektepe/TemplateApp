package kodz.org.core.domain.models

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class RowItemModel(
    @SerializedName("isVisible") val isVisible: Boolean? = null,
    @SerializedName("rowName") val rowName: String? = null,
    @SerializedName("dataModel") val dataModel: JsonObject? = null,
)