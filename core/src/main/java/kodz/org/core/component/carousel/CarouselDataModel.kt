package kodz.org.core.component.carousel

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.component.BaseDataModel
import kodz.org.core.component.carousel_item.CarouselItemDataModel

data class CarouselDataModel(
    @SerializedName("itemList", alternate = ["itemlist"]) val itemList: List<CarouselItemDataModel>
) : BaseDataModel()