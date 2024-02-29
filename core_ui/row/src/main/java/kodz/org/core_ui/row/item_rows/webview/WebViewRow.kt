package kodz.org.core_ui.row.item_rows.webview

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.row.BaseItemRow
import kodz.org.core.base.row.datamodel.BaseRowDataModel
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass

class WebViewRow(
    override var dataModel: BaseRowDataModel,
    override var isInCarousel: Boolean? = null,
    override var isInList: Boolean?
) : BaseItemRow() {
    override val layout: Int = R.layout.row_webview
    override val dataClass: KClass<*> = WebViewRowDataModel::class
    override val contractor = WebViewRowContractor(isInCarousel, isInList)
    override var binding: ViewDataBinding? = null
}