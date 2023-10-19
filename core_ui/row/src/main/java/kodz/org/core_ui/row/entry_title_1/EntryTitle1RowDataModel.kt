package kodz.org.core_ui.row.entry_title_1

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.BaseRowDataModel
import kodz.org.core.model.ItemClickEventModel

data class EntryTitle1RowDataModel(
    @SerializedName("titleText") val titleText: String? = null,
    @SerializedName("clickEventModel") override val itemClickEventModel: ItemClickEventModel? = null
) : BaseRowDataModel()