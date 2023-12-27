package kodz.org.core_ui.row.item_rows.webview

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.row.BaseItemRow
import kodz.org.core.base.row.datamodel.BaseRowDataModel
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass

class WebViewRow(
    override var dataModel: BaseRowDataModel,
    override var isInSlider: Boolean? = null,
    override var isInList: Boolean?
) : BaseItemRow() {
    override val layout: Int = R.layout.row_webview
    override val dataClass: KClass<*> = WebViewRowRowDataModel::class
    override val contractor = WebViewRowContractor(isInSlider, isInList)
    override var binding: ViewDataBinding? = null
}