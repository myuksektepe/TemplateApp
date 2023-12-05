package kodz.org.core_ui.row.video_player_2

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.BaseRowDataModel
import kodz.org.core.model.ItemClickEventModel


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 9.10.2023.
 */
data class VideoPlayerRowDataModel2(
    @SerializedName("itemClickEventModel") override val itemClickEventModel: ItemClickEventModel?,
    @SerializedName("thumbnailUrl") val thumbnailUrl: String?,
    @SerializedName("videoUrl") val videoUrl: String?,
    @SerializedName("autoPlay") val autoPlay: Boolean?,
    @SerializedName("isControllersVisible") val isControllersVisible: Boolean?
) : BaseRowDataModel()