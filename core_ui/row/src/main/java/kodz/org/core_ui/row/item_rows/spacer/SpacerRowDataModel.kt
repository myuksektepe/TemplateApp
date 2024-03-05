package kodz.org.core_ui.row.item_rows.spacer

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.datamodel.BaseItemRowDataModel
import kodz.org.core.domain.models.ClickEventModel

class SpacerRowDataModel(
    @SerializedName("clickEventModel") override val clickEventModel: ClickEventModel?,
    @SerializedName("height") val height: Int = 16
) : BaseItemRowDataModel()