package kodz.org.core.domain.enums

import com.google.gson.annotations.SerializedName

enum class EventTypeCode(code: String?) {

    @SerializedName("001")
    OPEN_SCREEN("001"),

    @SerializedName("002")
    GO_URL("002"),

    @SerializedName("010")
    SEND_HTTP_POST("010"),

    @SerializedName("011")
    SEND_HTTP_GET("011"),

    @SerializedName("020")
    SHOW_ALERT_DIALOG("020"),

    @SerializedName("021")
    OPEN_SETTINGS("021"),

    @SerializedName("030")
    RETRY_LAST_ACTION("030"),

    @SerializedName("090")
    CLOSE_THE_DIALOG("090"),

    @SerializedName("091")
    CLOSE_THE_SCREEN("091"),

    @SerializedName("092")
    CLOSE_THE_APP("092");

    companion object {
        fun from(value: String): EventTypeCode? {
            return EventTypeCode.values().firstOrNull { it.name == value }
        }
    }
}