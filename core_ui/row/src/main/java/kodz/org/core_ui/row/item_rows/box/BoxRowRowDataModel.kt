package kodz.org.core_ui.row.item_rows.box

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.datamodel.BaseItemRowDataModel
import kodz.org.core.model.ItemClickEventModel

class BoxRowRowDataModel(
    @SerializedName("itemClickEventModel") override val itemClickEventModel: ItemClickEventModel?,
    @SerializedName("boxType") val boxType: BoxType? = BoxType.SQUARE,
    @SerializedName("cornersType") val cornersType: CornersType? = CornersType.ROUNDED,
    @SerializedName("backgroundImageUrl") val backgroundImageUrl: String? = null,
    @SerializedName("backgroundColor") val backgroundColor: String? = null,
    @SerializedName("isGradientVisible") val gradientIsVisible: Boolean? = true,
    @SerializedName("textColor") val textColor: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("description") val description: String? = null,
) : BaseItemRowDataModel()