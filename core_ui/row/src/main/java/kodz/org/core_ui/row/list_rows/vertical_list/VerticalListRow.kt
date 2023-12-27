package kodz.org.core_ui.row.list_rows.vertical_list

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.BaseRow
import kodz.org.core.base.row.BaseRowDataModel
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass

class VerticalListRow(
    override var dataModel: BaseRowDataModel,
    override var isInSlider: Boolean? = null
) : BaseRow() {
    override val layout: Int = R.layout.row_vertical_list
    override val dataClass: KClass<*> = VerticalListRowDataModel::class
    override val contractor = VerticalListRowContractor()
    override var binding: ViewDataBinding? = null
}