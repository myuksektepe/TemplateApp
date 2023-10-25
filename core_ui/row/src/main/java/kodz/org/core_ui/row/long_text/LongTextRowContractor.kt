package kodz.org.core_ui.row.long_text

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.BaseRowContractor
import kodz.org.core_ui.row.databinding.RowLongTextBinding


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 6.10.2023.
 */
class LongTextRowContractor : BaseRowContractor() {
    override var itemClickHandler: ItemClickHandler? = null
    override var binding: ViewDataBinding? = null

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        binding = viewDataBinding
        initRow()
    }

    private fun initRow() {
        (binding as? RowLongTextBinding)?.run {
            data?.let { data ->
                longText.text = data.text
            }
        }
    }
}