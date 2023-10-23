package kodz.org.core.model

import com.google.gson.annotations.SerializedName


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 23.10.2023.
 */
enum class TextWeightType(weight: String) {
    @SerializedName("thin")
    THIN("thin"),

    @SerializedName("normal")
    NORMAL("normal"),

    @SerializedName("bold")
    BOLD("bold"),
}