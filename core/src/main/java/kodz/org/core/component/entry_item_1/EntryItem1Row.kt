package kodz.org.core.component.entry_item_1

import androidx.databinding.ViewDataBinding
import kodz.org.core.R
import kodz.org.core.base.component.ComponentBaseDataModel
import kodz.org.core.base.component.ComponentBaseRow
import kodz.org.core.base.component.ComponentBaseViewModel
import kotlin.reflect.KClass

class EntryItem1Row(override var dataModel: ComponentBaseDataModel) : ComponentBaseRow() {
    override val layout: Int = R.layout.component_entry_item_1
    override val dataClass: KClass<*> = EntryItem1DataModel::class
    override val viewModel: ComponentBaseViewModel = EntryItem1ViewModel()
    override val component = EntryItem1Contractor()
    override var binding: ViewDataBinding? = null
}