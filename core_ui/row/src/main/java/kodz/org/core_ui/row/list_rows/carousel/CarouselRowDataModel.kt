package kodz.org.core_ui.row.list_rows.carousel

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.datamodel.BaseListRowDataModel
import kodz.org.core.model.ItemClickEventModel


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 1.11.2023.
 */
class CarouselRowDataModel(
    @SerializedName("clickEventModel") override val itemClickEventModel: ItemClickEventModel? = null,
    @SerializedName("showIndicator", alternate = ["showindicator"]) val showIndicator: Boolean?,
    override val itemType: String?,
    override val itemList: List<JsonObject>?
) : BaseListRowDataModel()