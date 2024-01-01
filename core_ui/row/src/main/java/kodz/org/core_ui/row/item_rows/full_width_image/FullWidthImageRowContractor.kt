package kodz.org.core_ui.row.item_rows.full_width_image

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.contractor.BaseItemRowContractor
import kodz.org.core.extension.makeSlidable
import kodz.org.core.extension.setSpamProtectedClickListener
import kodz.org.core_ui.row.databinding.RowFullWidthImageBinding


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 25.10.2023.
 */
class FullWidthImageRowContractor(
    override val isInCarousel: Boolean? = null,
    override val isInList: Boolean? = null
) : BaseItemRowContractor() {
    override var viewBinding: ViewDataBinding? = null
    lateinit var binding: RowFullWidthImageBinding
    override var itemClickHandler: ItemClickHandler? = null

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        viewBinding = if (isInCarousel == true) viewDataBinding.makeSlidable() else viewDataBinding
        binding = viewDataBinding as RowFullWidthImageBinding
        initRow()
    }

    private fun initRow() {
        binding.run {
            data?.let { data ->

                data.height?.takeIf { it.isNotEmpty() }?.let {
                    imgFullWidth.layoutParams.height = it.toInt()
                    imgFullWidth.requestLayout()
                }

                // EventHandler
                imgFullWidth.setSpamProtectedClickListener {
                    itemClickHandler?.onItemClick(data.itemClickEventModel)
                }

            }
        }
    }
}