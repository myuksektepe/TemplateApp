package kodz.org.core.component.carousel

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.component.ComponentBaseDataModel
import kodz.org.core.component.carousel_item.CarouselItemDataModel
import kodz.org.core.model.ClickEventModel

data class CarouselDataModel(
    @SerializedName("itemList", alternate = ["itemlist"]) val itemList: List<CarouselItemDataModel>,
    @SerializedName("clickEventModel") override val clickEventModel: ClickEventModel? = null
) : ComponentBaseDataModel()