package kodz.org.core_ui.row.slider

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.BaseRow
import kodz.org.core.base.row.BaseRowDataModel
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 1.11.2023.
 */
class SliderRow(
    override var dataModel: BaseRowDataModel,
    override var isInSlider: Boolean? = null
) : BaseRow() {
    override val layout: Int = R.layout.row_slider
    override val dataClass: KClass<*> = SliderRowDataModel::class
    override val contractor = SliderRowContractor()
    override var binding: ViewDataBinding? = null
}