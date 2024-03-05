package kodz.org.core_ui.row.item_rows.html_view

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.datamodel.BaseRowDataModel
import kodz.org.core.base.row.row.BaseItemRow
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass

class HtmlViewRow(
    override var dataModel: BaseRowDataModel,
    override var isInCarousel: Boolean?,
    override var isInList: Boolean?,
) : BaseItemRow() {
    override val layout: Int = R.layout.row_compose_view
    override val dataClass: KClass<*> = HtmlViewRowDataModel::class
    override val contractor = HtmlViewRowContractor(isInCarousel, isInList)
    override var binding: ViewDataBinding? = null
}