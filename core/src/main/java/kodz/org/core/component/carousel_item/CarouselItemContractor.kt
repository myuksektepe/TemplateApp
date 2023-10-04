package kodz.org.core.component.carousel_item

import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import kodz.org.core.R
import kodz.org.core.base.component.ComponentBaseContractor
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.databinding.ComponentCarouselItemBinding
import kodz.org.core.extension.setSpamProtectedClickListener

class CarouselItemContractor : ComponentBaseContractor() {
    override var binding: ViewDataBinding? = null
    override var itemClickHandler: ItemClickHandler? = null

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        binding = viewDataBinding
        initComponent()
    }

    private fun initComponent() {
        (binding as? ComponentCarouselItemBinding)?.run {
            data?.let { data ->

                // Image
                Glide.with(this.img.context)
                    .load(data.imageUrl)
                    .error(R.drawable.placeholder)
                    .into(img)

                // EventHandler
                this.crsItemCard.setSpamProtectedClickListener {
                    itemClickHandler?.onItemClick(data.clickEventModel)
                }

            }
        }
    }
}