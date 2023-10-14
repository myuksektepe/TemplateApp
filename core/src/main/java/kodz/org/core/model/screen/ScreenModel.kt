package kodz.org.core.model.screen

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.data.http.HttpBaseResponse
import kodz.org.core.base.row.BaseRow
import kodz.org.core.model.http.ErrorModel

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