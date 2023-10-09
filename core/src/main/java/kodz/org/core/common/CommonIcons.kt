package kodz.org.core.common

import com.google.gson.annotations.SerializedName
import kodz.org.core.R

enum class CommonIcons(val imageName: String, val resourceId: Int) {

    @SerializedName("goBack", alternate = ["back"])
    GO_BACK("goBack", R.drawable.ic_back),

    @SerializedName("refresh", alternate = ["tryAgain"])
    REFRESH("refresh", R.drawable.ic_refresh),

    @SerializedName("close", alternate = ["exit"])
    CLOSE("close", R.drawable.ic_close),

    @SerializedName("home")
    HOME("home", R.drawable.ic_home);

    companion object {
        fun from(value: String): CommonIcons? {
            return values().firstOrNull { it.imageName == value }

            /*
            return CommonIcons.values().firstOrNull {
                CommonIcons::class.java.getField(it.name)
                    .getAnnotation(SerializedName::class.java)?.value == iconName || CommonIcons::class.java.getField(
                    it.name
                )
                    .getAnnotation(SerializedName::class.java)?.alternate?.find { alternate -> alternate == iconName }
                    .toBoolean()
            }
             */
        }
    }
}