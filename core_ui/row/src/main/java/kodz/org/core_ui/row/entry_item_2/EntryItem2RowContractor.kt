package kodz.org.core_ui.row.entry_item_2

import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import kodz.org.core.R
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.BaseRowContractor
import kodz.org.core.extension.setSpamProtectedClickListener
import kodz.org.core_ui.row.databinding.RowEntryItem2Binding

class EntryItem2RowContractor : BaseRowContractor() {
    override var binding: ViewDataBinding? = null
    override var itemClickHandler: ItemClickHandler? = null

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        binding = viewDataBinding
        initRow()
    }

    private fun initRow() {
        (binding as? RowEntryItem2Binding)?.run {
            data?.let { data ->

                // Title
                txtEntryTitle.text = data.title

                // Image
                Glide.with(this.imgEntry.context)
                    .load(data.imageUrl)
                    .error(R.drawable.placeholder)
                    .into(imgEntry.imageView)

                // EventHandler
                itemLayout.setSpamProtectedClickListener {
                    itemClickHandler?.onItemClick(data.itemClickEventModel)
                }

            }
        }
    }
}