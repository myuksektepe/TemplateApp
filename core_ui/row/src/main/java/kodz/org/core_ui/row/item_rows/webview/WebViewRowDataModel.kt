package kodz.org.core_ui.row.item_rows.webview

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.datamodel.BaseItemRowDataModel
import kodz.org.core.model.ClickEventModel

data class WebViewRowDataModel(
    @SerializedName("content") val content: String? = null,
    @SerializedName("clickEventModel") override val clickEventModel: ClickEventModel? = null
) : BaseItemRowDataModel()