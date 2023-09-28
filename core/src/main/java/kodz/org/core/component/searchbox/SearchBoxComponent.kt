package kodz.org.core.component.searchbox

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.component.BaseComponent
import kodz.org.core.base.handler.ItemClickHandler

class SearchBoxComponent : BaseComponent() {
    override var eventHandler: ItemClickHandler? = null
    override var binding: ViewDataBinding? = null

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        binding = viewDataBinding
        initComponent()
    }

    private fun initComponent() {

    }
}