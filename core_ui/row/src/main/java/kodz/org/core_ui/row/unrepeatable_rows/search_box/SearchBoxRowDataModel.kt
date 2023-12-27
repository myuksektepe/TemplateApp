package kodz.org.core_ui.row.unrepeatable_rows.search_box

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.handler.SearchHandler
import kodz.org.core.base.row.BaseRowDataModel
import kodz.org.core.model.ItemClickEventModel

class SearchBoxRowDataModel : BaseRowDataModel() {
    @SerializedName("clickEventModel")
    override val itemClickEventModel: ItemClickEventModel? = null
    var searchHandler: SearchHandler? = null
}