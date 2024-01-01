package kodz.org.core_ui.row.item_rows.long_text

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.contractor.BaseItemRowContractor
import kodz.org.core.common.consts.ZERO
import kodz.org.core.extension.makeSlidable
import kodz.org.core_ui.row.databinding.RowLongTextBinding


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 6.10.2023.
 */
class LongTextRowContractor(
    override val isInCarousel: Boolean? = null,
    override val isInList: Boolean? = null
) : BaseItemRowContractor() {
    override var viewBinding: ViewDataBinding? = null
    lateinit var binding: RowLongTextBinding
    override var itemClickHandler: ItemClickHandler? = null

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        viewBinding = if (isInCarousel == true) viewDataBinding.makeSlidable() else viewDataBinding
        binding = viewDataBinding as RowLongTextBinding
        initRow()
    }

    private fun initRow() {
        binding.run {
            data?.let { data ->

                // Paddings
                if (isInList == true || isInCarousel == true) txtLongText.setPadding(ZERO, ZERO, ZERO, ZERO)

                // Text
                txtLongText.text = data.text
            }
        }
    }
}