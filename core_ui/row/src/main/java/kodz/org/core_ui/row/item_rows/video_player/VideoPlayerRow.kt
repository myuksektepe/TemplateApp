package kodz.org.core_ui.row.item_rows.video_player

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.row.BaseItemRow
import kodz.org.core.base.row.contractor.BaseRowContractor
import kodz.org.core.base.row.datamodel.BaseRowDataModel
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass

/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 9.10.2023.
 */
class VideoPlayerRow(
    override var dataModel: BaseRowDataModel,
    override var isInCarousel: Boolean? = null,
    override var isInList: Boolean?
) : BaseItemRow() {
    override val layout: Int = R.layout.row_video_player
    override val dataClass: KClass<*> = VideoPlayerRowRowDataModel::class
    override val contractor: BaseRowContractor = VideoPlayerRowContractor(isInCarousel, isInList)
    override var binding: ViewDataBinding? = null
}