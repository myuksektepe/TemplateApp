package kodz.org.core_ui.row.categories_slider.categories_slider_item

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.BaseRowContractor
import kodz.org.core.extension.setSpamProtectedClickListener
import kodz.org.core_ui.row.databinding.RowCategoriesSliderItemBinding


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 31.10.2023.
 */
class CategoriesSliderItemRowContractor : BaseRowContractor() {
    override var itemClickHandler: ItemClickHandler? = null
    override var binding: ViewDataBinding? = null

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        binding = viewDataBinding
        initRow()
    }

    private fun initRow() {
        (binding as? RowCategoriesSliderItemBinding)?.run {
            this.data?.let { data ->

                // Text
                txtCategoryName.text = data.categoryName

                // Image
                data.iconUrl?.let { crcCategoryImage.setImageUrl(it) }

                // Event Handler
                cnsCategory.setSpamProtectedClickListener {
                    itemClickHandler?.onItemClick(data.itemClickEventModel)
                }
            }
        }
    }
}