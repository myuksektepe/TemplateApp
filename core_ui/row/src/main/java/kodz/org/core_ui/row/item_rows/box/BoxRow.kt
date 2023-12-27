package kodz.org.core_ui.row.item_rows.box

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.row.BaseItemRow
import kodz.org.core.base.row.datamodel.BaseRowDataModel
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass

class BoxRow(
    override var dataModel: BaseRowDataModel,
    override var isInSlider: Boolean?,
    override var isInList: Boolean?,
) : BaseItemRow() {
    override val layout: Int = R.layout.row_box
    override val dataClass: KClass<*> = BoxRowRowDataModel::class
    override val contractor = BoxRowContractor(isInSlider, isInList)
    override var binding: ViewDataBinding? = null
}