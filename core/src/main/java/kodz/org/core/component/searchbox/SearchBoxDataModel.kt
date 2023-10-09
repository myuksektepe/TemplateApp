package kodz.org.core.component.searchbox

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.component.ComponentBaseDataModel
import kodz.org.core.base.handler.SearchHandler
import kodz.org.core.model.screen.ItemClickEventModel

class SearchBoxDataModel : ComponentBaseDataModel() {
    @SerializedName("clickEventModel")
    override val itemClickEventModel: ItemClickEventModel? = null
    var searchHandler: SearchHandler? = null
}