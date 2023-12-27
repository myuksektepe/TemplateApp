package kodz.org.core_ui.row.item_rows.video_player

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.BaseRow
import kodz.org.core.base.row.BaseRowContractor
import kodz.org.core.base.row.BaseRowDataModel
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass

/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 9.10.2023.
 */
class VideoPlayerRow(
    override var dataModel: BaseRowDataModel,
    override var isInSlider: Boolean? = null
) : BaseRow() {
    override val layout: Int = R.layout.row_video_player
    override val dataClass: KClass<*> = VideoPlayerRowDataModel::class
    override val contractor: BaseRowContractor = VideoPlayerRowContractor()
    override var binding: ViewDataBinding? = null
}