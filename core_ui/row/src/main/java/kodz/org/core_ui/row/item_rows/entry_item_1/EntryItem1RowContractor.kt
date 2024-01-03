package kodz.org.core_ui.row.item_rows.entry_item_1

import androidx.databinding.ViewDataBinding
import kodz.org.core.GlideApp
import kodz.org.core.R
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.contractor.BaseItemRowContractor
import kodz.org.core.extension.makeSlidable
import kodz.org.core.extension.prepareForGroup
import kodz.org.core.extension.setSpamProtectedClickListener
import kodz.org.core_ui.row.databinding.RowEntryItem1Binding

class EntryItem1RowContractor(
    override val isInCarousel: Boolean? = null,
    override val isInList: Boolean? = null
) : BaseItemRowContractor() {
    override var viewBinding: ViewDataBinding? = null
    lateinit var binding: RowEntryItem1Binding
    override var itemClickHandler: ItemClickHandler? = null

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        viewBinding = if (isInCarousel == true) viewDataBinding.makeSlidable() else viewDataBinding
        binding = viewDataBinding as RowEntryItem1Binding
        initRow()
    }

    private fun initRow() {
        binding.run {
            data?.let { data ->

                // Paddings
                rowEntryItem1Root.prepareForGroup(isInList, isInCarousel)

                // Title
                txtEntryTitle.text = data.title

                // Image
                GlideApp.with(this.imgEntry.context)
                    .load(data.imageUrl)
                    .error(R.drawable.placeholder)
                    .into(imgEntry.imageView)

                // EventHandler
                rowEntryItem1Root.setSpamProtectedClickListener {
                    itemClickHandler?.onItemClick(data.itemClickEventModel)
                }

            }
        }
    }
}