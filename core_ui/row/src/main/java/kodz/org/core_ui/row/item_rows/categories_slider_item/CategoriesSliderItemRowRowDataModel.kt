package kodz.org.core_ui.row.item_rows.categories_slider_item

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.datamodel.BaseItemRowDataModel
import kodz.org.core.model.ItemClickEventModel


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 31.10.2023.
 */
data class CategoriesSliderItemRowRowDataModel(
    @SerializedName("clickEventModel") override val itemClickEventModel: ItemClickEventModel?,
    @SerializedName("id") val id: String?,
    @SerializedName("iconUrl") val iconUrl: String?,
    @SerializedName("categoryName") val categoryName: String?,
) : BaseItemRowDataModel()