package kodz.org.feature.dashboard.data.model

import com.google.gson.annotations.SerializedName

data class RowItemModel(
    @SerializedName("rowName") val rowName: String? = null,
    @SerializedName("dataModelName") val dataModelName: String? = null,
    @SerializedName("dataModel") val dataModel: String? = null,
)