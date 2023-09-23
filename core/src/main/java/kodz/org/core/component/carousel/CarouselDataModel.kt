package kodz.org.core.component.carousel

import kodz.org.core.base.component.BaseDataModel
import kodz.org.core.component.carousel_item.CarouselItemRow

data class CarouselDataModel(
    val list: List<CarouselItemRow>
) : BaseDataModel