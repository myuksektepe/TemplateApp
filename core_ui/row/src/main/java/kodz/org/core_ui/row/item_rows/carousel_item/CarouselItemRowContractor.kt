package kodz.org.core_ui.row.item_rows.carousel_item

import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import kodz.org.core.R
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.contractor.BaseItemRowContractor
import kodz.org.core.extension.makeSlidable
import kodz.org.core.extension.setSpamProtectedClickListener
import kodz.org.core_ui.row.databinding.RowCarouselItemBinding

class CarouselItemRowContractor(
    override val isInSlider: Boolean? = null,
    override val isInList: Boolean? = null
) : BaseItemRowContractor() {
    override var viewBinding: ViewDataBinding? = null
    lateinit var binding: RowCarouselItemBinding
    override var itemClickHandler: ItemClickHandler? = null

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        // Is In Slider?
        viewBinding = if (isInSlider == true) viewDataBinding.makeSlidable() else viewDataBinding
        binding = viewDataBinding as RowCarouselItemBinding
        initRow()
    }

    private fun initRow() {
        binding.run {
            data?.let { data ->
                // Image
                Glide.with(this.img.context)
                    .load(data.imageUrl)
                    .error(R.drawable.placeholder)
                    .into(img)

                // EventHandler
                crsItemCard.setSpamProtectedClickListener {
                    itemClickHandler?.onItemClick(data.itemClickEventModel)
                }
            }
        }
    }
}