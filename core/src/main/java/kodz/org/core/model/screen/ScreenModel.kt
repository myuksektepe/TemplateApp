package kodz.org.core.model.screen

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.data.http.HttpBaseResponse

open class ScreenModel(
    @SerializedName("settings") val settings: SettingsModel? = null,
    @SerializedName("rows") val rows: ArrayList<RowItemModel>? = null
) : HttpBaseResponse()