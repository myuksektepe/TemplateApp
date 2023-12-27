package kodz.org.core_ui.row.item_rows.entry_item_1

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.row.BaseItemRow
import kodz.org.core.base.row.datamodel.BaseRowDataModel
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass

class EntryItem1Row(
    override var dataModel: BaseRowDataModel,
    override var isInSlider: Boolean? = null,
    override var isInList: Boolean?
) : BaseItemRow() {
    override val layout: Int = R.layout.row_entry_item_1
    override val dataClass: KClass<*> = EntryItem1RowRowDataModel::class
    override val contractor = EntryItem1RowContractor(isInSlider, isInList)
    override var binding: ViewDataBinding? = null
}