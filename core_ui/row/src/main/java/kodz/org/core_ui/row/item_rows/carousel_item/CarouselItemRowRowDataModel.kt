package kodz.org.core_ui.row.item_rows.carousel_item

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.datamodel.BaseItemRowDataModel
import kodz.org.core.model.ItemClickEventModel

data class CarouselItemRowRowDataModel(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("imageUrl") val imageUrl: String?,
    @SerializedName("clickEventModel") override val itemClickEventModel: ItemClickEventModel? = null,
    val isInSlider: Boolean = true
) : BaseItemRowDataModel()