package kodz.org.core_ui.row.entry_item_2

import androidx.databinding.ViewDataBinding
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kodz.org.core.GlideApp
import kodz.org.core.R
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.BaseRowContractor
import kodz.org.core.extension.makeSlidable
import kodz.org.core.extension.setSpamProtectedClickListener
import kodz.org.core_ui.row.databinding.RowEntryItem2Binding

class EntryItem2RowContractor(
    private val isInSlider: Boolean? = null
) : BaseRowContractor() {
    override var viewBinding: ViewDataBinding? = null
    override val binding by lazy { viewBinding as? RowEntryItem2Binding }
    override var itemClickHandler: ItemClickHandler? = null

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        viewBinding = if (isInSlider == true) viewDataBinding.makeSlidable() else viewDataBinding
        initRow()
    }

    private fun initRow() {
        binding?.run {
            data?.let { data ->
                // Title
                txtEntryTitle.text = data.title

                // Image
                GlideApp.with(this.imgEntry.context)
                    .load(data.imageUrl)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
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