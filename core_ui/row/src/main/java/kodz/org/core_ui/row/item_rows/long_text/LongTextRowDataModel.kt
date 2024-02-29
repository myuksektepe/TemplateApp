package kodz.org.core_ui.row.item_rows.long_text

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.datamodel.BaseItemRowDataModel
import kodz.org.core.model.ClickEventModel


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 6.10.2023.
 */
data class LongTextRowDataModel(
    @SerializedName("clickEventModel") override val clickEventModel: ClickEventModel? = null,
    @SerializedName("text") val text: String? = null
) : BaseItemRowDataModel()