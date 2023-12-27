package kodz.org.core_ui.row.item_rows.quote

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.BaseRowContractor
import kodz.org.core.extension.gone
import kodz.org.core.extension.makeSlidable
import kodz.org.core.extension.setSpamProtectedClickListener
import kodz.org.core.extension.visible
import kodz.org.core_ui.row.databinding.RowQuoteBinding


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 25.10.2023.
 */
class QuoteRowContractor(
    private val isInSlider: Boolean? = null
) : BaseRowContractor() {
    override var viewBinding: ViewDataBinding? = null
    lateinit var binding: RowQuoteBinding
    override var itemClickHandler: ItemClickHandler? = null

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        viewBinding = if (isInSlider == true) viewDataBinding.makeSlidable() else viewDataBinding
        binding = viewDataBinding as RowQuoteBinding
        initRow()
    }

    private fun initRow() {
        binding.run {
            data?.let { data ->
                // Author Visibility
                data.author?.takeIf { it.isNotEmpty() }?.let {
                    txtAuthor.visible()
                } ?: run {
                    txtAuthor.gone()
                }

                // EventHandler
                txtQuote.setSpamProtectedClickListener {
                    itemClickHandler?.onItemClick(data.itemClickEventModel)
                }

            }
        }
    }
}