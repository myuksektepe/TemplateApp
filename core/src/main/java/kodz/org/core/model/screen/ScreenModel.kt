package kodz.org.core.model.screen

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.data.http.HttpBaseResponse
import kodz.org.core.model.http.ErrorModel

open class ScreenModel(
    @SerializedName("settings") val settings: SettingsModel? = null,
    @SerializedName("error") val error: ErrorModel? = null,
    @SerializedName("rows") val rows: ArrayList<RowItemModel>? = null
) : HttpBaseResponse()