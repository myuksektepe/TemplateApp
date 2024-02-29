package kodz.org.core_ui.row.list_rows.vertical_list

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.datamodel.BaseListRowDataModel
import kodz.org.core.model.ClickEventModel

class VerticalListRowDataModel(
    @SerializedName("clickEventModel") override val clickEventModel: ClickEventModel?,
    @SerializedName("listType") val listType: ListType? = ListType.GRID,
    @SerializedName("columnCount") val columnCount: Int? = 1,
    @SerializedName("itemCount") val itemCount: Int? = 6,
    @SerializedName("isShowMoreButtonVisible") val isShowMoreButtonVisible: Boolean? = true,
    @SerializedName("showMoreItemCount") val showMoreItemCount: Int? = 4,
    @SerializedName("itemList", alternate = ["itemlist"]) override val itemList: List<JsonObject>?,
    @SerializedName("itemType", alternate = ["itemtype"]) override val itemType: String?
) : BaseListRowDataModel()