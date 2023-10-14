package kodz.org.core_ui.row.carousel

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.BaseRowDataModel
import kodz.org.core.model.screen.ItemClickEventModel
import kodz.org.core_ui.row.carousel_item.CarouselItemRowDataModel

data class CarouselRowDataModel(
    @SerializedName("itemList", alternate = ["itemlist"]) val itemList: List<CarouselItemRowDataModel>,
    @SerializedName("clickEventModel") override val itemClickEventModel: ItemClickEventModel? = null
) : BaseRowDataModel()