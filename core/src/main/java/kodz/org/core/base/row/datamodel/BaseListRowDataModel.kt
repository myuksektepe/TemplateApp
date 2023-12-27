package kodz.org.core.base.row.datamodel

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

abstract class BaseListRowDataModel : BaseRowDataModel() {
    @get:SerializedName("itemList", alternate = ["itemlist"])
    abstract val itemList: List<JsonObject>?

    @get:SerializedName("itemType", alternate = ["itemtype"])
    abstract val itemType: String?
}