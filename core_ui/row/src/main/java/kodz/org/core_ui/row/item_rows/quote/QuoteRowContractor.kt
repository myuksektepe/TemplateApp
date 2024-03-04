package kodz.org.core_ui.row.item_rows.quote

import androidx.databinding.ViewDataBinding
import kodz.org.core.domain.interfaces.handler.ItemClickHandler
import kodz.org.core.base.row.contractor.BaseItemRowContractor
import kodz.org.core.domain.extensions.gone
import kodz.org.core.domain.extensions.makeSlidable
import kodz.org.core.domain.extensions.prepareForGroup
import kodz.org.core.domain.extensions.setSpamProtectedClickListener
import kodz.org.core.domain.extensions.visible
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
                crdQuote.prepareForGroup(isInList, isInCarousel)

                // Author Visibility
                data.author?.takeIf { it.isNotEmpty() }?.let {
                    txtAuthor.visible()
                } ?: run {
                    txtAuthor.gone()
                }

                // EventHandler
                txtQuote.setSpamProtectedClickListener {
                    itemClickHandler?.onItemClick(data.clickEventModel)
                }

            }
        }
    }
}