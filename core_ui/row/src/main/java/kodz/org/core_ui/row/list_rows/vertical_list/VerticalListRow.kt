package kodz.org.core_ui.row.list_rows.vertical_list

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.row.BaseListRow
import kodz.org.core.base.row.datamodel.BaseRowDataModel
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass

class VerticalListRow(
    override var dataModel: BaseRowDataModel
) : BaseListRow() {
    override val layout: Int = R.layout.row_vertical_list
    override val dataClass: KClass<*> = VerticalListRowDataModel::class
    override val contractor = VerticalListRowContractor()
    override var binding: ViewDataBinding? = null
}