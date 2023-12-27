package kodz.org.core_ui.row.item_rows.entry_item_1

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.BaseRowDataModel
import kodz.org.core.model.ItemClickEventModel

class EntryItem1RowDataModel(
    @SerializedName("clickEventModel") override val itemClickEventModel: ItemClickEventModel? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("imageUrl") val imageUrl: String? = null,
) : BaseRowDataModel()