package kodz.org.core_ui.row.item_rows.categories_slider_item

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.datamodel.BaseItemRowDataModel
import kodz.org.core.model.ClickEventModel


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 31.10.2023.
 */
data class CategoriesSliderItemRowDataModel(
    @SerializedName("clickEventModel") override val clickEventModel: ClickEventModel?,
    @SerializedName("id") val id: String?,
    @SerializedName("iconUrl") val iconUrl: String?,
    @SerializedName("categoryName") val categoryName: String?,
) : BaseItemRowDataModel()