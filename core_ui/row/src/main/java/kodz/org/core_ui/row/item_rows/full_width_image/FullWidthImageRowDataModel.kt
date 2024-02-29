package kodz.org.core_ui.row.item_rows.full_width_image

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.datamodel.BaseItemRowDataModel
import kodz.org.core.model.ClickEventModel


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 25.10.2023.
 */
class FullWidthImageRowDataModel(
    @SerializedName("clickEventModel") override val clickEventModel: ClickEventModel? = null,
    @SerializedName("imageUrl") val imageUrl: String? = null,
    @SerializedName("height") val height: String? = null,
) : BaseItemRowDataModel()