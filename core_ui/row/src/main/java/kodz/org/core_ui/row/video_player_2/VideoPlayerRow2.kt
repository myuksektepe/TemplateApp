package kodz.org.core_ui.row.video_player_2

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.BaseRow
import kodz.org.core.base.row.BaseRowContractor
import kodz.org.core.base.row.BaseRowDataModel
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass

/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 9.10.2023.
 */
class VideoPlayerRow2(
    override var dataModel: BaseRowDataModel,
    override var isInSlider: Boolean? = null
) : BaseRow() {
    override val layout: Int = R.layout.row_video_player_2
    override val dataClass: KClass<*> = VideoPlayerRowDataModel2::class
    override val contractor: BaseRowContractor = VideoPlayerRowContractor2()
    override var binding: ViewDataBinding? = null
}