package kodz.org.core.component.entry_title_1

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.component.ComponentBaseDataModel
import kodz.org.core.model.screen.ItemClickEventModel

data class EntryTitle1DataModel(
    @SerializedName("titleText") val titleText: String? = null,
    @SerializedName("clickEventModel") override val itemClickEventModel: ItemClickEventModel? = null
) : ComponentBaseDataModel()