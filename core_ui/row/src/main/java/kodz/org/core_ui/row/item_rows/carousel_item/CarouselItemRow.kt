package kodz.org.core_ui.row.item_rows.carousel_item

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.datamodel.BaseRowDataModel
import kodz.org.core.base.row.row.BaseItemRow
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass

class CarouselItemRow(
    override var dataModel: BaseRowDataModel,
    override var isInSlider: Boolean? = null,
    override var isInList: Boolean?
) : BaseItemRow() {
    override val layout = R.layout.row_carousel_item
    override val dataClass: KClass<*> = CarouselItemRowRowDataModel::class
    override val contractor = CarouselItemRowContractor(isInSlider, isInList)
    override var binding: ViewDataBinding? = null
}