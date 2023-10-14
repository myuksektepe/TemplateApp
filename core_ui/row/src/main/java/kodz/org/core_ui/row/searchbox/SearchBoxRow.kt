package kodz.org.core_ui.row.searchbox

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.BaseRow
import kodz.org.core.base.row.BaseRowDataModel
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass

class SearchBoxRow(
    override var dataModel: BaseRowDataModel
) : BaseRow() {
    override val layout = R.layout.row_searchbox
    override val dataClass: KClass<*> = SearchBoxRowDataModel::class
    override val contractor = SearchBoxRowContractor()
    override var binding: ViewDataBinding? = null
}