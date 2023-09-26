package kodz.org.core.component.carousel_item

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.component.BaseDataModel

data class CarouselItemDataModel(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("imageUrl") val imageUrl: String? = null,
) : BaseDataModel()