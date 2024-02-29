package kodz.org.core_ui.row.unrepeatable_item_rows.entry_title_1

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.datamodel.BaseRowDataModel
import kodz.org.core.base.row.row.BaseUnrepeatableItemRow
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass

class EntryTitle1Row(
    override var dataModel: BaseRowDataModel,
) : BaseUnrepeatableItemRow() {
    override val layout: Int = R.layout.row_entry_title_1
    override val dataClass: KClass<*> = EntryTitle1RowDataModel::class
    override val contractor = EntryTitle1RowContractor()
    override var binding: ViewDataBinding? = null
}