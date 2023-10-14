package kodz.org.core_ui.row.entry_title_1

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.BaseRow
import kodz.org.core.base.row.BaseRowDataModel
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass

class EntryTitle1Row(override var dataModel: BaseRowDataModel) : BaseRow() {
    override val layout: Int = R.layout.row_entry_title_1
    override val dataClass: KClass<*> = EntryTitle1RowDataModel::class
    override val contractor = EntryTitle1RowContractor()
    override var binding: ViewDataBinding? = null
}