package kodz.org.core_ui.row.vertical_list

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.BaseRowDataModel
import kodz.org.core.model.ItemClickEventModel

class VerticalListRowDataModel(
    @SerializedName("clickEventModel") override val itemClickEventModel: ItemClickEventModel?,
    @SerializedName("listType") val listType: ListType? = ListType.GRID,
    @SerializedName("columnCount") val columnCount: Int? = 1,
    @SerializedName("itemCount") val itemCount: Int? = 6,
    @SerializedName("isShowMoreButtonVisible") val isShowMoreButtonVisible: Boolean? = true,
    @SerializedName("showMoreItemCount") val showMoreItemCount: Int? = 4,
    @SerializedName("itemList") val itemList: List<JsonObject>?,
    @SerializedName("itemType") val itemType: String?,
) : BaseRowDataModel()