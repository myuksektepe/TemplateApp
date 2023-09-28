package kodz.org.core.component.entry_item_2

import androidx.databinding.ViewDataBinding
import kodz.org.core.R
import kodz.org.core.base.component.ComponentBaseDataModel
import kodz.org.core.base.component.ComponentBaseRow
import kodz.org.core.base.component.ComponentBaseViewModel
import kotlin.reflect.KClass

class EntryItem2Row(override var dataModel: ComponentBaseDataModel) : ComponentBaseRow() {
    override val layout: Int = R.layout.component_entry_item_2
    override val dataClass: KClass<*> = EntryItem2DataModel::class
    override val viewModel: ComponentBaseViewModel = EntryItem2ViewModel()
    override val component = EntryItem2Contractor()
    override var binding: ViewDataBinding? = null
}