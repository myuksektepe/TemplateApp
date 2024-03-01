package kodz.org.core_ui.row.item_rows.entry_item_1

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.datamodel.BaseItemRowDataModel
import kodz.org.core.domain.models.ClickEventModel

class EntryItem1RowDataModel(
    @SerializedName("clickEventModel") override val clickEventModel: ClickEventModel? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("imageUrl") val imageUrl: String? = null,
    @SerializedName("isShadowVisible") val isShadowVisible: Boolean? = false,
    ) : BaseItemRowDataModel()