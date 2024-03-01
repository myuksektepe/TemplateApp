package kodz.org.core_ui.row.unrepeatable_item_rows.search_box

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.datamodel.BaseUnrepeatableItemRowDataModel
import kodz.org.core.domain.models.ClickEventModel

class SearchBoxRowDataModel(
    @SerializedName("clickEventModel")
    override val clickEventModel: ClickEventModel? = null,
) : BaseUnrepeatableItemRowDataModel()