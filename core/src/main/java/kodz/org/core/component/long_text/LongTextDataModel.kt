package kodz.org.core.component.long_text

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.component.ComponentBaseDataModel
import kodz.org.core.model.screen.ClickEventModel


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 6.10.2023.
 */
data class LongTextDataModel(
    @SerializedName("text") val text: String? = null,
    @SerializedName("clickEventModel") override val clickEventModel: ClickEventModel? = null
) : ComponentBaseDataModel()