package kodz.org.core_ui.row.list_rows.horizontal_list

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.datamodel.BaseListRowDataModel
import kodz.org.core.model.ItemClickEventModel


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 30.10.2023.
 */
data class HorizontalListRowDataModel(
    @SerializedName("clickEventModel") override val itemClickEventModel: ItemClickEventModel?,
    override val itemList: List<JsonObject>?,
    override val itemType: String?
) : BaseListRowDataModel() {

}