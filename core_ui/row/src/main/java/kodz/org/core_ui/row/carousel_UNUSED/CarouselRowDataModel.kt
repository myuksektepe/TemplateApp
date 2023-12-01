package kodz.org.core_ui.row.carousel_UNUSED

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.BaseRowDataModel
import kodz.org.core.model.ItemClickEventModel
import kodz.org.core_ui.row.carousel_UNUSED.carousel_item.CarouselItemRowDataModel

data class CarouselRowDataModel(
    @SerializedName("clickEventModel") override val itemClickEventModel: ItemClickEventModel? = null,
    @SerializedName("showIndicator", alternate = ["showindicator"]) val showIndicator: Boolean?,
    @SerializedName("itemList", alternate = ["itemlist"]) val itemList: List<CarouselItemRowDataModel>?
) : BaseRowDataModel()