package kodz.org.core_ui.row.categories_slider

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.BaseRowDataModel
import kodz.org.core.model.ItemClickEventModel
import kodz.org.core_ui.row.categories_slider.categories_slider_item.CategoriesSliderItemRowDataModel


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 30.10.2023.
 */
data class CategoriesSliderRowDataModel(
    @SerializedName("clickEventModel") override val itemClickEventModel: ItemClickEventModel?,
    @SerializedName("itemList", alternate = ["itemlist"]) val itemList: List<CategoriesSliderItemRowDataModel>?
) : BaseRowDataModel()