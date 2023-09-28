package kodz.org.core.component.searchbox

import androidx.databinding.ViewDataBinding
import kodz.org.core.R
import kodz.org.core.base.component.BaseDataModel
import kodz.org.core.base.component.BaseRow
import kotlin.reflect.KClass

class SearchBoxRow(
    override var dataModel: BaseDataModel
) : BaseRow() {
    override val layout = R.layout.component_searchbox
    override val dataClass: KClass<*> = SearchBoxDataModel::class
    override val viewModel = SearchBoxViewModel()
    override val component = SearchBoxComponent()
    override var binding: ViewDataBinding? = null
}