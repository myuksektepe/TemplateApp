package kodz.org.core.component.entry_item_2

import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import kodz.org.core.R
import kodz.org.core.base.component.ComponentBaseContractor
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.databinding.ComponentEntryItem2Binding
import kodz.org.core.extension.setSpamProtectedClickListener

class EntryItem2Contractor : ComponentBaseContractor() {
    override var binding: ViewDataBinding? = null
    override var itemClickHandler: ItemClickHandler? = null

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        binding = viewDataBinding
        initComponent()
    }

    private fun initComponent() {
        (binding as? ComponentEntryItem2Binding)?.run {
            data?.let { data ->

                // Title
                txtEntryTitle.text = data.title

                // Image
                Glide.with(this.imgEntry.context)
                    .load(data.imageUrl)
                    .error(R.drawable.placeholder)
                    .into(imgEntry)

                // EventHandler
                itemLayout.setSpamProtectedClickListener {
                    itemClickHandler?.onItemClick(data.clickEventModel)
                }

            }
        }
    }
}