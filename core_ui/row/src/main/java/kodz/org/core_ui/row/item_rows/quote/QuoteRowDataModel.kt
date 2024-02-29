package kodz.org.core_ui.row.item_rows.quote

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.datamodel.BaseItemRowDataModel
import kodz.org.core.model.ItemClickEventModel


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 25.10.2023.
 */
data class QuoteRowDataModel(
    @SerializedName("itemClickEventModel") override val itemClickEventModel: ItemClickEventModel? = null,
    @SerializedName("text") val text: String? = null,
    @SerializedName("author") val author: String? = null,
    @SerializedName("showBackground") val showBackground: Boolean? = null,
    @SerializedName("isShadowVisible") val isShadowVisible: Boolean? = false,
) : BaseItemRowDataModel()