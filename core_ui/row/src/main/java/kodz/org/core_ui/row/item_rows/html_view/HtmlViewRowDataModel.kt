package kodz.org.core_ui.row.item_rows.html_view

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.datamodel.BaseItemRowDataModel
import kodz.org.core.domain.models.ClickEventModel

class HtmlViewRowDataModel(
    @SerializedName("clickEventModel") override val clickEventModel: ClickEventModel?,
    @SerializedName("html") val html: String?
) : BaseItemRowDataModel()