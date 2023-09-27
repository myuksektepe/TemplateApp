package kodz.org.core.component.carousel_item

import androidx.databinding.ViewDataBinding
import kodz.org.core.R
import kodz.org.core.base.component.BaseDataModel
import kodz.org.core.base.component.BaseRow
import kotlin.reflect.KClass

class CarouselItemRow(
    override var dataModel: BaseDataModel
) : BaseRow() {
    override val layout = R.layout.component_carousel_item
    override val dataClass: KClass<*> = CarouselItemDataModel::class
    override val viewModel = CarouselItemViewModel()
    override val component = CarouselItemComponent()
    override var binding: ViewDataBinding? = null
}