package kodz.org.core_ui.row.item_rows.expandable_box

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.datamodel.BaseItemRowDataModel
import kodz.org.core.model.ItemClickEventModel
import kodz.org.core_ui.row.item_rows.box.CornersType

class ExpandableBoxRowDataModel(
    @SerializedName("itemClickEventModel") override val itemClickEventModel: ItemClickEventModel?,
    @SerializedName("cornersType") val cornersType: CornersType? = CornersType.ROUNDED,
    @SerializedName("backgroundColor") val backgroundColor: String? = null,
    @SerializedName("textColor") val textColor: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("description") val description: String? = null,
) : BaseItemRowDataModel() {
}