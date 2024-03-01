package kodz.org.core_ui.row.unrepeatable_item_rows.entry_title_1

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.datamodel.BaseUnrepeatableItemRowDataModel
import kodz.org.core.domain.models.ClickEventModel
import kodz.org.core.domain.enums.TextAlignType
import kodz.org.core.domain.enums.TextWeightType

data class EntryTitle1RowDataModel(
    @SerializedName("titleText") val titleText: String? = null,
    @SerializedName("textAlign") val textAlign: TextAlignType? = TextAlignType.CENTER,
    @SerializedName("textWeight") val textWeight: TextWeightType? = TextWeightType.BOLD,
    @SerializedName("clickEventModel") override val clickEventModel: ClickEventModel? = null
) : BaseUnrepeatableItemRowDataModel()