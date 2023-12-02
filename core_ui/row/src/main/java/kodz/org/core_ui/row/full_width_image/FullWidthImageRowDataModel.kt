package kodz.org.core_ui.row.full_width_image

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.BaseRowDataModel
import kodz.org.core.model.ItemClickEventModel


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 25.10.2023.
 */
class FullWidthImageRowDataModel() : BaseRowDataModel() {
    @SerializedName("clickEventModel") override val itemClickEventModel: ItemClickEventModel? = null
    @SerializedName("imageUrl") val imageUrl: String? = null
    @SerializedName("height") val height: String? = null
}