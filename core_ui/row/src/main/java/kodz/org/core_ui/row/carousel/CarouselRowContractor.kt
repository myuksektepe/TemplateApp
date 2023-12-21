package kodz.org.core_ui.row.carousel

import androidx.databinding.ViewDataBinding
import com.google.android.material.tabs.TabLayoutMediator
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.BaseRowContractor
import kodz.org.core.extension.gone
import kodz.org.core.extension.visible
import kodz.org.core_ui.row.common.MultipleTypeAdapter
import kodz.org.core_ui.row.common.getItemListByRowType
import kodz.org.core_ui.row.databinding.RowCarouselBinding


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 1.11.2023.
 */
class CarouselRowContractor : BaseRowContractor() {
    override var viewBinding: ViewDataBinding? = null
    lateinit var binding: RowCarouselBinding
    override var itemClickHandler: ItemClickHandler? = null
    private val sliderAdapter by lazy { MultipleTypeAdapter() }

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        viewBinding = viewDataBinding
        binding = viewDataBinding as RowCarouselBinding
        initRow()
    }

    private fun initRow() {
        binding.run {
            data?.let { data ->

                if (!sliderAdapter.hasStableIds()) {
                    sliderAdapter.setHasStableIds(true)
                }

                // Item List
                data.itemList?.let {
                    viewPagerVertical.adapter = sliderAdapter

                    // Indicator Dots
                    if (data.showIndicator == true) {
                        tabLayout.visible()
                        TabLayoutMediator(tabLayout, viewPagerVertical) { _, _ -> }.attach()
                    } else tabLayout.gone()

                    // Items
                    val itemList = data.itemType?.let { data.itemList.getItemListByRowType(it, itemClickHandler, true) }
                    sliderAdapter.submitList(itemList)

                } ?: kotlin.run {
                    binding.root.gone()
                }

            }
        }
    }
}