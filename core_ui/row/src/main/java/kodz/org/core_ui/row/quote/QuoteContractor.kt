package kodz.org.core_ui.row.quote

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
class QuoteContractor(
    private val isInSlider: Boolean? = null
) : BaseRowContractor() {
    override var itemClickHandler: ItemClickHandler? = null
    override var binding: ViewDataBinding? = null

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        binding = viewDataBinding
        initRow()
    }

    private fun initRow() {
        (binding as? RowQuoteBinding)?.run {
            data?.let { data ->

                if (isInSlider == true) this.makeSlidable()

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