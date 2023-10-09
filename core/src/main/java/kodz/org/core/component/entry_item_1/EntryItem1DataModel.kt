package kodz.org.core.component.entry_item_1

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.component.ComponentBaseDataModel
import kodz.org.core.model.screen.ItemClickEventModel

class EntryItem1DataModel(
    @SerializedName("clickEventModel") override val itemClickEventModel: ItemClickEventModel? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("imageUrl") val imageUrl: String? = null,
) : ComponentBaseDataModel()