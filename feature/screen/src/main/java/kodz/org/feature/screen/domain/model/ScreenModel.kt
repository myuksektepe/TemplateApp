package kodz.org.feature.screen.domain.model

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.row.BaseRow
import kodz.org.core.domain.models.ErrorModel
import kodz.org.core.domain.models.RowItemModel

class ScreenModel(
    @SerializedName("settings") val settings: SettingsModel? = null,
    @SerializedName("error") val error: ErrorModel? = null,
    @SerializedName("rows") var rows: ArrayList<RowItemModel>? = null
) {
    class ViewEntity(
        val settings: SettingsModel? = null,
        val error: ErrorModel.ViewEntity? = null,
        val rows: MutableList<BaseRow?>? = null,
        val tabs: List<TabModel>? = null,
    )
}