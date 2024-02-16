package kodz.org.core_ui.row.unrepeatable_item_rows.tabs_layout

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.datamodel.BaseUnrepeatableItemRowDataModel
import kodz.org.core.common.enums.CommonIcons
import kodz.org.core.model.ItemClickEventModel
import kodz.org.core.model.RowItemModel

class TabsLayoutDataModel(
    @SerializedName("itemClickEventModel") override val itemClickEventModel: ItemClickEventModel? = null,
    @SerializedName("tabs") val tabs: List<TabModel>?,
) : BaseUnrepeatableItemRowDataModel()

class TabModel(
    @SerializedName("tabTitle") val tabTitle: String?,
    @SerializedName("tabIcon") val tabIcon: CommonIcons?,
    @SerializedName("tabContent") val tabContent: List<RowItemModel?>?
)