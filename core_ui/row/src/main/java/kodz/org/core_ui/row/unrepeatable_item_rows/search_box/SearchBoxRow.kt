package kodz.org.core_ui.row.unrepeatable_item_rows.search_box

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.datamodel.BaseRowDataModel
import kodz.org.core.base.row.row.BaseUnrepeatableItemRow
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass

class SearchBoxRow(
    override var dataModel: BaseRowDataModel
) : BaseUnrepeatableItemRow() {
    override val layout = R.layout.row_search_box
    override val dataClass: KClass<*> = SearchBoxRowDataModel::class
    override val contractor = SearchBoxRowContractor()
    override var binding: ViewDataBinding? = null
}