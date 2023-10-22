package kodz.org.core_ui.row.carousel.carousel_item

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.BaseRow
import kodz.org.core.base.row.BaseRowDataModel
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass

class CarouselItemRow(
    override var dataModel: BaseRowDataModel
) : BaseRow() {
    override val layout = R.layout.row_carousel_item
    override val dataClass: KClass<*> = CarouselItemRowDataModel::class
    override val contractor = CarouselItemRowContractor()
    override var binding: ViewDataBinding? = null
}