package kodz.org.core.component.entry_item_1

import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import kodz.org.core.R
import kodz.org.core.base.component.ComponentBaseContractor
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.databinding.ComponentEntryItem1Binding
import kodz.org.core.extension.setSpamProtectedClickListener

class EntryItem1Contractor : ComponentBaseContractor() {
    override var binding: ViewDataBinding? = null
    override var eventHandler: ItemClickHandler? = null

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        binding = viewDataBinding
        initComponent()
    }

    private fun initComponent() {
        (binding as? ComponentEntryItem1Binding)?.run {
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
                    eventHandler?.onItemClick(data.clickEventModel)
                }

            }
        }
    }
}