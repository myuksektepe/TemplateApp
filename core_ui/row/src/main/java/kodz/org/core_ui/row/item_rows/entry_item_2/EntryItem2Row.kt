package kodz.org.core_ui.row.item_rows.entry_item_2

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.row.BaseItemRow
import kodz.org.core.base.row.datamodel.BaseRowDataModel
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass

class EntryItem2Row(
    override var dataModel: BaseRowDataModel,
    override var isInCarousel: Boolean? = null,
    override var isInList: Boolean?
) : BaseItemRow() {
    override val layout: Int = R.layout.row_entry_item_2
    override val dataClass: KClass<*> = EntryItem2RowDataModel::class
    override val contractor = EntryItem2RowContractor(isInCarousel, isInList)
    override var binding: ViewDataBinding? = null
}