package kodz.org.core_ui.row.item_rows.entry_title_1

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.datamodel.BaseItemRowDataModel
import kodz.org.core.model.ItemClickEventModel
import kodz.org.core.model.TextAlignType
import kodz.org.core.model.TextWeightType

data class EntryTitle1RowRowDataModel(
    @SerializedName("titleText") val titleText: String? = null,
    @SerializedName("textAlign") val textAlign: TextAlignType? = TextAlignType.CENTER,
    @SerializedName("textWeight") val textWeight: TextWeightType? = TextWeightType.BOLD,
    @SerializedName("clickEventModel") override val itemClickEventModel: ItemClickEventModel? = null
) : BaseItemRowDataModel()