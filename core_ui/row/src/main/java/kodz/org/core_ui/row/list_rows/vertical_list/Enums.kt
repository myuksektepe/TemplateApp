package kodz.org.core_ui.row.list_rows.vertical_list

import com.google.gson.annotations.SerializedName

enum class ListType(type: String) {
    @SerializedName("grid")
    GRID("grid"),

    @SerializedName("staggered")
    STAGGERED("staggered")
}