package kodz.org.core_ui.row.item_rows.video_player

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.datamodel.BaseItemRowDataModel
import kodz.org.core.model.ClickEventModel


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 9.10.2023.
 */
data class VideoPlayerRowDataModel(
    @SerializedName("clickEventModel") override val clickEventModel: ClickEventModel?,
    @SerializedName("thumbnailUrl") val thumbnailUrl: String?,
    @SerializedName("videoUrl") val videoUrl: String?,
    @SerializedName("autoPlay") val autoPlay: Boolean?,
    @SerializedName("isControllersVisible") val isControllersVisible: Boolean?,
    @SerializedName("isShadowVisible") val isShadowVisible: Boolean? = false,
    ) : BaseItemRowDataModel()