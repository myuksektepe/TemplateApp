package kodz.org.core.component.searchbox

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.component.ComponentBaseDataModel
import kodz.org.core.model.ClickEventModel

class SearchBoxDataModel : ComponentBaseDataModel() {
    @SerializedName("clickEventModel")
    override val clickEventModel: ClickEventModel? = null
}