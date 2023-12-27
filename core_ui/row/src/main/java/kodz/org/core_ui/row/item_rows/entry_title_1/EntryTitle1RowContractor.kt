package kodz.org.core_ui.row.item_rows.entry_title_1

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.BaseRowContractor
import kodz.org.core_ui.row.databinding.RowEntryTitle1Binding

class EntryTitle1RowContractor() : BaseRowContractor() {
    override var viewBinding: ViewDataBinding? = null
    lateinit var binding: RowEntryTitle1Binding
    override var itemClickHandler: ItemClickHandler? = null

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        viewBinding = viewDataBinding
        binding = viewDataBinding as RowEntryTitle1Binding
    }
}