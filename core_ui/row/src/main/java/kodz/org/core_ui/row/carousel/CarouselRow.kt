package kodz.org.core_ui.row.carousel

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.BaseRow
import kodz.org.core.base.row.BaseRowDataModel
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass

class CarouselRow(
    override var dataModel: BaseRowDataModel,
    override var isInSlider: Boolean? = null
) : BaseRow() {
    override val layout: Int = R.layout.row_carousel
    override val dataClass: KClass<*> = CarouselRowDataModel::class
    override val contractor = CarouselRowContractor()
    override var binding: ViewDataBinding? = null
}