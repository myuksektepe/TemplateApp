package kodz.org.core.common

import com.google.gson.annotations.SerializedName
import kodz.org.core.R

enum class CommonIcons(val imageName: String, val resourceId: Int) {

    @SerializedName("home")
    HOME("home", R.drawable.ic_home),

    @SerializedName("goBack", alternate = ["back"])
    GO_BACK("goBack", R.drawable.ic_back),

    @SerializedName("refresh", alternate = ["tryAgain"])
    REFRESH("refresh", R.drawable.ic_refresh),

    @SerializedName("close", alternate = ["exit"])
    CLOSE("close", R.drawable.ic_close),

    @SerializedName("doubleArrowRight")
    DOUBLE_ARROW_RIGHT("doubleArrowRight", R.drawable.ic_double_arrow_right),

    @SerializedName("eye", alternate = ["visible", "view"])
    EYE("eye", R.drawable.ic_eye),

    @SerializedName("forward10", alternate = ["forward"])
    FORWARD10("forward10", R.drawable.ic_forward_10),

    @SerializedName("replay10", alternate = ["replay"])
    REPLAY("replay10", R.drawable.ic_replay_10),

    @SerializedName("love", alternate = ["heart", "like"])
    LOVE("love", R.drawable.ic_love),

    @SerializedName("pause")
    PAUSE("pause", R.drawable.ic_pause),

    @SerializedName("play")
    PLAY("play", R.drawable.ic_play),

    @SerializedName("search")
    SEARCH("search", R.drawable.ic_search),

    @SerializedName("questionMark")
    QUESTION_MARK("questionMark", R.drawable.ic_question_mark),

    @SerializedName("done", alternate = ["okay", "tick"])
    DONE("done", R.drawable.ic_done);

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