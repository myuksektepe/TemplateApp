package kodz.org.feature.dashboard.data

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.data.http.HttpBaseResponse
import kodz.org.core.common.CommonIcons
import kodz.org.core.model.RowItemModel

data class DashboardResponse(
    @SerializedName("settings") val settings: SettingsModel? = null,
    @SerializedName("rows") val rows: ArrayList<RowItemModel>? = null
) : HttpBaseResponse()

data class SettingsModel(
    @SerializedName("type") val type: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("subTitle") val subTitle: String? = null,
    @SerializedName("customIcon") val customIcon: CommonIcons? = null,
    @SerializedName("isBackIconVisible") val isBackIconVisible: Boolean? = null,
    @SerializedName("isSearchBoxVisible") val isSearchBoxVisible: Boolean? = null,
)