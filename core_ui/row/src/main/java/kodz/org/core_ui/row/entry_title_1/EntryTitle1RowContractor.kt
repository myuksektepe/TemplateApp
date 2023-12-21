package kodz.org.core_ui.row.entry_title_1

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.BaseRowContractor
import kodz.org.core_ui.row.databinding.RowEntryTitle1Binding

class EntryTitle1RowContractor() : BaseRowContractor() {
    override var viewBinding: ViewDataBinding? = null
    override val binding by lazy { viewBinding as? RowEntryTitle1Binding }
    override var itemClickHandler: ItemClickHandler? = null

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        viewBinding = viewDataBinding
    }
}