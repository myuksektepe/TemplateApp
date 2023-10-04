package kodz.org.core.model.screen

import com.google.gson.annotations.SerializedName
import kodz.org.core.common.CommonIcons

data class SettingsModel(
    @SerializedName("type") val type: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("subTitle") val subTitle: String? = null,
    @SerializedName("customIcon") val customIcon: CommonIcons? = null,
    @SerializedName("isBackIconVisible") val isBackIconVisible: Boolean? = null,
    @SerializedName("isSearchBoxVisible") val isSearchBoxVisible: Boolean? = null,
)