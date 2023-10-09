package kodz.org.core.component.videoplayer

import androidx.databinding.ViewDataBinding
import kodz.org.core.R
import kodz.org.core.base.component.ComponentBaseContractor
import kodz.org.core.base.component.ComponentBaseDataModel
import kodz.org.core.base.component.ComponentBaseRow
import kotlin.reflect.KClass


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 9.10.2023.
 */
class VideoPlayerRow(override var dataModel: ComponentBaseDataModel) : ComponentBaseRow() {
    override val layout: Int = R.layout.component_video_player
    override val dataClass: KClass<*> = VideoPlayerDataModel::class
    override val contractor: ComponentBaseContractor = VideoPlayerContractor()
    override var binding: ViewDataBinding? = null
}