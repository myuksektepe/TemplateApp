package kodz.org.core_ui.row.full_width_image

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.BaseRow
import kodz.org.core.base.row.BaseRowDataModel
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 25.10.2023.
 */
class FullWidthImageRow(
    override var dataModel: BaseRowDataModel,
    override var isInSlider: Boolean? = null
) : BaseRow() {
    override val layout: Int = R.layout.row_full_width_image
    override val dataClass: KClass<*> = FullWidthImageDataModel::class
    override val contractor = FullWidthImageContractor()
    override var binding: ViewDataBinding? = null
}