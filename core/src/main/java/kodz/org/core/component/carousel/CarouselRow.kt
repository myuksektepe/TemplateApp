package kodz.org.core.component.carousel

import androidx.databinding.ViewDataBinding
import kodz.org.core.R
import kodz.org.core.base.component.BaseDataModel
import kodz.org.core.base.component.BaseRow
import kotlin.reflect.KClass

class CarouselRow(
    override var dataModel: BaseDataModel
) : BaseRow() {
    override val layout: Int = R.layout.component_carousel
    override val dataClass: KClass<*> = CarouselDataModel::class
    override val viewModel = CarouselViewModel()
    override val component = CarouselComponent()
    override var binding: ViewDataBinding? = null
}