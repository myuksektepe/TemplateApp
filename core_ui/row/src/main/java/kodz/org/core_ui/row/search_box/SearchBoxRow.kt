package kodz.org.core_ui.row.search_box

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.BaseRow
import kodz.org.core.base.row.BaseRowDataModel
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass

class SearchBoxRow(
    override var dataModel: BaseRowDataModel,
    override var isInSlider: Boolean? = null
) : BaseRow() {
    override val layout = R.layout.row_search_box
    override val dataClass: KClass<*> = SearchBoxRowDataModel::class
    override val contractor = SearchBoxRowContractor()
    override var binding: ViewDataBinding? = null
}