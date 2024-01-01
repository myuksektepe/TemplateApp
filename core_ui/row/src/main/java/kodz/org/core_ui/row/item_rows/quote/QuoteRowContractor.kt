package kodz.org.core_ui.row.item_rows.quote

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.contractor.BaseItemRowContractor
import kodz.org.core.common.consts.ZERO
import kodz.org.core.extension.gone
import kodz.org.core.extension.makeSlidable
import kodz.org.core.extension.setSpamProtectedClickListener
import kodz.org.core.extension.visible
import kodz.org.core_ui.row.databinding.RowQuoteBinding


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 25.10.2023.
 */
class QuoteRowContractor(
    override val isInCarousel: Boolean? = null,
    override val isInList: Boolean? = null
) : BaseItemRowContractor() {
    override var viewBinding: ViewDataBinding? = null
    lateinit var binding: RowQuoteBinding
    override var itemClickHandler: ItemClickHandler? = null

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        viewBinding = if (isInCarousel == true) viewDataBinding.makeSlidable() else viewDataBinding
        binding = viewDataBinding as RowQuoteBinding
        initRow()
    }

    private fun initRow() {
        binding.run {
            data?.let { data ->

                // Paddings
                if (isInList == true || isInCarousel == true) rowQuoteRoot.setPadding(ZERO, ZERO, ZERO, ZERO)

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