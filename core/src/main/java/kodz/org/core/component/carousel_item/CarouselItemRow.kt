package kodz.org.core.component.carousel_item

import androidx.databinding.ViewDataBinding
import kodz.org.core.R
import kodz.org.core.base.component.ComponentBaseDataModel
import kodz.org.core.base.component.ComponentBaseRow
import kotlin.reflect.KClass

class CarouselItemRow(
    override var dataModel: ComponentBaseDataModel
) : ComponentBaseRow() {
    override val layout = R.layout.component_carousel_item
    override val dataClass: KClass<*> = CarouselItemDataModel::class
    override val contractor = CarouselItemContractor()
    override var binding: ViewDataBinding? = null
    // override val viewModel = CarouselItemViewModel()
}