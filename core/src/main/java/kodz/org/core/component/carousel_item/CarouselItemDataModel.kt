package kodz.org.core.component.carousel_item

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.component.ComponentBaseDataModel
import kodz.org.core.model.screen.ItemClickEventModel

data class CarouselItemDataModel(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("imageUrl") val imageUrl: String? = null,
    @SerializedName("clickEventModel") override val itemClickEventModel: ItemClickEventModel? = null
) : ComponentBaseDataModel()