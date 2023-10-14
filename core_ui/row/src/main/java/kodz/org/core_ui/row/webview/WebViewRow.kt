package kodz.org.core_ui.row.webview

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.BaseRow
import kodz.org.core.base.row.BaseRowDataModel
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass

class WebViewRow(override var dataModel: BaseRowDataModel) : BaseRow() {
    override val layout: Int = R.layout.row_webview
    override val dataClass: KClass<*> = WebViewRowDataModel::class
    override val contractor = WebViewRowContractor()
    override var binding: ViewDataBinding? = null
}