package kodz.org.core_ui.row.categories_slider.categories_slider_item

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.BaseRowDataModel
import kodz.org.core.model.ItemClickEventModel


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 31.10.2023.
 */
data class CategoriesSliderItemRowDataModel(
    @SerializedName("clickEventModel") override val itemClickEventModel: ItemClickEventModel?,
    @SerializedName("id") val id: String?,
    @SerializedName("iconUrl") val iconUrl: String?,
    @SerializedName("categoryName") val categoryName: String?,
) : BaseRowDataModel()