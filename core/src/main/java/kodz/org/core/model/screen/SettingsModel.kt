package kodz.org.core.model.screen

import com.google.gson.annotations.SerializedName

data class SettingsModel(
    @SerializedName("type") val type: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("subTitle") val subTitle: String? = null,
    @SerializedName("customIcon") val customIcon: String? = null,
    @SerializedName("isBackIconVisible") val isBackIconVisible: Boolean? = null,
    @SerializedName("isSearchBoxVisible") val isSearchBoxVisible: Boolean? = null,
)