package kodz.org.core.model

import com.google.gson.annotations.SerializedName

data class RowItemModel(
    @SerializedName("rowName") val rowName: String? = null,
    @SerializedName("dataModel") val dataModel: String? = null,
)