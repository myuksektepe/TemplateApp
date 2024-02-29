package kodz.org.core_ui.row.item_rows.entry_item_2

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.datamodel.BaseItemRowDataModel
import kodz.org.core.model.ClickEventModel

class EntryItem2RowDataModel(
    @SerializedName("clickEventModel") override val clickEventModel: ClickEventModel? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("imageUrl") val imageUrl: String? = null,
    @SerializedName("isShadowVisible") val isShadowVisible: Boolean? = false,
    ) : BaseItemRowDataModel()