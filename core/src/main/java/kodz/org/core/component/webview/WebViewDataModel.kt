package kodz.org.core.component.webview

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.component.ComponentBaseDataModel
import kodz.org.core.model.screen.ClickEventModel

data class WebViewDataModel(
    @SerializedName("content") val content: String? = null,
    @SerializedName("clickEventModel") override val clickEventModel: ClickEventModel? = null
) : ComponentBaseDataModel()