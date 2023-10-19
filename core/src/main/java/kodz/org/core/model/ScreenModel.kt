package kodz.org.core.model

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.data.http.HttpBaseResponse
import kodz.org.core.base.row.BaseRow
import kodz.org.core.common.CommonIcons

open class ScreenModel(
    @SerializedName("settings") val settings: SettingsModel? = null,
    @SerializedName("error") val error: ErrorModel? = null,
    @SerializedName("rows") var rows: ArrayList<RowItemModel>? = null
) : HttpBaseResponse() {
    class ViewEntity(
        val settings: SettingsModel? = null,
        val error: ErrorModel? = null,
        val rows: MutableList<BaseRow>? = null
    )
}

// =====================================================
/* SETTINGS */
data class SettingsModel(
    @SerializedName("type") val type: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("subTitle") val subTitle: String? = null,
    @SerializedName("customIcon") val customIcon: String? = null,
    @SerializedName("isBackIconVisible") val isBackIconVisible: Boolean? = null,
    @SerializedName("isSearchBoxVisible") val isSearchBoxVisible: Boolean? = null,
)

// =====================================================
/* ERROR */
data class ErrorModel(
    @SerializedName("type") val type: ErrorType?,
    @SerializedName("title") val title: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("primaryButton") val primaryButton: ButtonModel? = null,
    @SerializedName("secondaryButton") val secondaryButton: ButtonModel? = null,
)

data class ButtonModel(
    @SerializedName("type") val type: ButtonType? = null,
    @SerializedName("text") val text: String? = null,
    @SerializedName("icon") val icon: CommonIcons? = null,
    @SerializedName("textColor") val textColor: String? = null,
    @SerializedName("backgroundColor") val backgroundColor: String? = null,
    @SerializedName("showUnderline") val showUnderline: Boolean? = false,
    @SerializedName("eventType") val eventType: EventTypeCode? = null,
)

// =====================================================
/* ROWS */
data class RowItemModel(
    @SerializedName("isVisible") val isVisible: Boolean? = null,
    @SerializedName("rowName") val rowName: String? = null,
    @SerializedName("dataModel") val dataModel: String? = null,
)