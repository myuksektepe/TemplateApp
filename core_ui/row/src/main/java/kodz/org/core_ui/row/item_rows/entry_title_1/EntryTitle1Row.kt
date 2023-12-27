package kodz.org.core_ui.row.item_rows.entry_title_1

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.row.BaseItemRow
import kodz.org.core.base.row.datamodel.BaseRowDataModel
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass

class EntryTitle1Row(
    override var dataModel: BaseRowDataModel,
    override var isInSlider: Boolean? = null,
    override var isInList: Boolean?
) : BaseItemRow() {
    override val layout: Int = R.layout.row_entry_title_1
    override val dataClass: KClass<*> = EntryTitle1RowRowDataModel::class
    override val contractor = EntryTitle1RowContractor(isInSlider, isInList)
    override var binding: ViewDataBinding? = null
}