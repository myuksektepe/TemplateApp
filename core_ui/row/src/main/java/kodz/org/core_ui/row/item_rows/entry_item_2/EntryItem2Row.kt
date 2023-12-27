package kodz.org.core_ui.row.item_rows.entry_item_2

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.BaseRow
import kodz.org.core.base.row.BaseRowDataModel
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass

class EntryItem2Row(
    override var dataModel: BaseRowDataModel,
    override var isInSlider: Boolean? = null
) : BaseRow() {
    override val layout: Int = R.layout.row_entry_item_2
    override val dataClass: KClass<*> = EntryItem2RowDataModel::class
    override val contractor = EntryItem2RowContractor(isInSlider)
    override var binding: ViewDataBinding? = null
}