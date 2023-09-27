package kodz.org.core.component.carousel_item

import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import kodz.org.core.R
import kodz.org.core.base.component.BaseComponent
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.databinding.ComponentCarouselItemBinding
import kodz.org.core.extension.setSpamProtectedClickListener

class CarouselItemComponent : BaseComponent() {
    override var binding: ViewDataBinding? = null
    override var eventHandler: ItemClickHandler? = null

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
                    eventHandler?.onItemClick(data.clickEventModel)
                }
            }
        }
    }
}