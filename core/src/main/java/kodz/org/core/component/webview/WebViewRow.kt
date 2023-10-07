package kodz.org.core.component.webview

import androidx.databinding.ViewDataBinding
import kodz.org.core.R
import kodz.org.core.base.component.ComponentBaseDataModel
import kodz.org.core.base.component.ComponentBaseRow
import kotlin.reflect.KClass

class WebViewRow(override var dataModel: ComponentBaseDataModel) : ComponentBaseRow() {
    override val layout: Int = R.layout.component_webview
    override val dataClass: KClass<*> = WebViewDataModel::class
    override val contractor = WebViewContractor()
    override var binding: ViewDataBinding? = null
}