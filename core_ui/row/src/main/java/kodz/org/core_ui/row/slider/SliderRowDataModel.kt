package kodz.org.core_ui.row.slider

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.BaseRowDataModel
import kodz.org.core.model.ItemClickEventModel


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 1.11.2023.
 */
class SliderRowDataModel(
    @SerializedName("clickEventModel") override val itemClickEventModel: ItemClickEventModel? = null,
    @SerializedName("showIndicator", alternate = ["showindicator"]) val showIndicator: Boolean?,
    @SerializedName("itemType", alternate = ["itemtype"]) val itemType: String?,
    @SerializedName("itemList", alternate = ["itemlist"]) val itemList: List<JsonObject>?
) : BaseRowDataModel()