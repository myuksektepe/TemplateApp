package kodz.org.core.model.http

import com.google.gson.annotations.SerializedName
import kodz.org.core.common.CommonIcons
import kodz.org.core.model.screen.EventTypeCode


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 8.10.2023.
 */
data class ButtonModel(
    @SerializedName("text") val text: String? = null,
    @SerializedName("icon") val icon: CommonIcons? = null,
    @SerializedName("textColor") val textColor: String? = null,
    @SerializedName("backgroundColor") val backgroundColor: String? = null,
    @SerializedName("eventType") val eventType: EventTypeCode? = null,
)