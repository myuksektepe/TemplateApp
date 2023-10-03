package kodz.org.core.model

import com.google.gson.annotations.SerializedName

data class RowItemModel(
    @SerializedName("isVisible") val isVisible: Boolean? = null,
    @SerializedName("rowName") val rowName: String? = null,
    @SerializedName("dataModel") val dataModel: String? = null,
)