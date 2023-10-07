package kodz.org.core.component.entry_title_1

import androidx.databinding.ViewDataBinding
import kodz.org.core.R
import kodz.org.core.base.component.ComponentBaseDataModel
import kodz.org.core.base.component.ComponentBaseRow
import kotlin.reflect.KClass

class EntryTitle1Row(override var dataModel: ComponentBaseDataModel) : ComponentBaseRow() {
    override val layout: Int = R.layout.component_entry_title_1
    override val dataClass: KClass<*> = EntryTitle1DataModel::class
    override val contractor = EntryTitle1Contractor()
    override var binding: ViewDataBinding? = null
}