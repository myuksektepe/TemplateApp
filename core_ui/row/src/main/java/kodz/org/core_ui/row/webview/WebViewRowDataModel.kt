package kodz.org.core_ui.row.webview

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.BaseRowDataModel
import kodz.org.core.model.ItemClickEventModel

data class WebViewRowDataModel(
    @SerializedName("content") val content: String? = null,
    @SerializedName("clickEventModel") override val itemClickEventModel: ItemClickEventModel? = null
) : BaseRowDataModel()