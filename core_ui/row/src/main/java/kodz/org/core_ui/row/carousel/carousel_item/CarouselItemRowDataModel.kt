package kodz.org.core_ui.row.carousel.carousel_item

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.BaseRowDataModel
import kodz.org.core.model.ItemClickEventModel

data class CarouselItemRowDataModel(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("imageUrl") val imageUrl: String? = null,
    @SerializedName("clickEventModel") override val itemClickEventModel: ItemClickEventModel? = null
) : BaseRowDataModel()