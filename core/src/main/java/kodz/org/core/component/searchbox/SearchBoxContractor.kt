package kodz.org.core.component.searchbox

import android.view.inputmethod.EditorInfo
import androidx.databinding.ViewDataBinding
import kodz.org.core.base.component.ComponentBaseContractor
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.databinding.ComponentSearchboxBinding

class SearchBoxContractor : ComponentBaseContractor() {
    override var eventHandler: ItemClickHandler? = null
    override var binding: ViewDataBinding? = null

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        binding = viewDataBinding
        initComponent()
    }

    private fun initComponent() {
        (binding as? ComponentSearchboxBinding)?.run {
            data?.let { data ->
                edtSearch.imeOptions = EditorInfo.IME_ACTION_DONE
            }
        }
    }
}