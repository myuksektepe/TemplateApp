package kodz.org.core_ui.row.item_rows.expandable_box

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.datamodel.BaseRowDataModel
import kodz.org.core.base.row.row.BaseItemRow
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass

class ExpandableBoxRow(
    override var dataModel: BaseRowDataModel,
    override var isInCarousel: Boolean?,
    override var isInList: Boolean?,
) : BaseItemRow() {
    override val layout: Int = R.layout.row_expandable_box
    override val dataClass: KClass<*> = ExpandableBoxRowDataModel::class
    override val contractor = ExpandableBoxRowContractor(isInCarousel, isInList)
    override var binding: ViewDataBinding? = null
}