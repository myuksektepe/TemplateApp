package kodz.org.core_ui.row.entry_title_1

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.BaseRowContractor

class EntryTitle1RowContractor() : BaseRowContractor() {
    override var itemClickHandler: ItemClickHandler? = null
    override var binding: ViewDataBinding? = null

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        binding = viewDataBinding
    }
}