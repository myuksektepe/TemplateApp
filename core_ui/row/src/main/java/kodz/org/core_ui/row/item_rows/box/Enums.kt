package kodz.org.core_ui.row.item_rows.box

import com.google.gson.annotations.SerializedName

enum class BoxType(val type: String) {
    @SerializedName("square")
    SQUARE("square"),

    @SerializedName("rectangle")
    RECTANGLE("rectangle");

    companion object {
        fun from(value: String): BoxType? {
            return BoxType.values().firstOrNull { it.type == value }
        }
    }
}

enum class CornersType(val type: String) {
    @SerializedName("rounded")
    ROUNDED("rounded"),

    @SerializedName("sharp")
    SHARP("sharp");

    companion object {
        fun from(value: String): CornersType? {
            return CornersType.values().firstOrNull { it.type == value }
        }
    }
}