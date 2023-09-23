package kodz.org.core.component.carousel_item

import kodz.org.core.base.component.BaseDataModel

data class CarouselItemDataModel(
    val id: Int,
    val title: String? = null,
    val description: String? = null,
    val imageUrl: String? = null,
) : BaseDataModel