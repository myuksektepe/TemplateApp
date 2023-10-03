package kodz.org.core.component.searchbox

import androidx.databinding.ViewDataBinding
import kodz.org.core.R
import kodz.org.core.base.component.ComponentBaseDataModel
import kodz.org.core.base.component.ComponentBaseRow
import kotlin.reflect.KClass

class SearchBoxRow(
    override var dataModel: ComponentBaseDataModel
) : ComponentBaseRow() {
    override val layout = R.layout.component_searchbox
    override val dataClass: KClass<*> = SearchBoxDataModel::class
    override val viewModel = SearchBoxViewModel()
    override val contractor = SearchBoxContractor()
    override var binding: ViewDataBinding? = null
}