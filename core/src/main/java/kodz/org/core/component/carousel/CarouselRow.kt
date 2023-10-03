package kodz.org.core.component.carousel

import androidx.databinding.ViewDataBinding
import kodz.org.core.R
import kodz.org.core.base.component.ComponentBaseDataModel
import kodz.org.core.base.component.ComponentBaseRow
import kotlin.reflect.KClass

class CarouselRow(
    override var dataModel: ComponentBaseDataModel
) : ComponentBaseRow() {
    override val layout: Int = R.layout.component_carousel
    override val dataClass: KClass<*> = CarouselDataModel::class
    override val viewModel = CarouselViewModel()
    override val contractor = CarouselContractor()
    override var binding: ViewDataBinding? = null
}