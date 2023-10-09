package kodz.org.core.component.videoplayer

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.component.ComponentBaseDataModel
import kodz.org.core.model.screen.ItemClickEventModel


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 9.10.2023.
 */
data class VideoPlayerDataModel(
    @SerializedName("itemClickEventModel") override val itemClickEventModel: ItemClickEventModel?,
    @SerializedName("thumbnailUrl") val thumbnailUrl: String?,
    @SerializedName("videoUrl") val videoUrl: String?,
    @SerializedName("autoPlay") val autoPlay: Boolean?,
    @SerializedName("isControllersVisible") val isControllersVisible: Boolean?
) : ComponentBaseDataModel()