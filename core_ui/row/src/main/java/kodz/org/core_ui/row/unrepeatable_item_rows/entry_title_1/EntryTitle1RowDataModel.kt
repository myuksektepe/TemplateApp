package kodz.org.core_ui.row.unrepeatable_item_rows.entry_title_1

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.datamodel.BaseUnrepeatableItemRowDataModel
import kodz.org.core.model.ItemClickEventModel
import kodz.org.core.model.TextAlignType
import kodz.org.core.model.TextWeightType

data class EntryTitle1RowDataModel(
    @SerializedName("titleText") val titleText: String? = null,
    @SerializedName("textAlign") val textAlign: TextAlignType? = TextAlignType.CENTER,
    @SerializedName("textWeight") val textWeight: TextWeightType? = TextWeightType.BOLD,
    @SerializedName("clickEventModel") override val itemClickEventModel: ItemClickEventModel? = null
) : BaseUnrepeatableItemRowDataModel()