package kodz.org.core_ui.row.list_rows.carousel

import androidx.databinding.ViewDataBinding
import com.google.android.material.tabs.TabLayoutMediator
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.contractor.BaseListRowContractor
import kodz.org.core.domain.extensions.gone
import kodz.org.core.domain.extensions.visible
import kodz.org.core_ui.row.common.MultipleTypeAdapter
import kodz.org.core_ui.row.common.getItemListByRowType
import kodz.org.core_ui.row.databinding.RowCarouselBinding


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 1.11.2023.
 */
class CarouselRowContractor : BaseListRowContractor() {
    override var viewBinding: ViewDataBinding? = null
    lateinit var binding: RowCarouselBinding
    override var itemClickHandler: ItemClickHandler? = null
    private val listAdapter by lazy { MultipleTypeAdapter() }

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        viewBinding = viewDataBinding
        binding = viewDataBinding as RowCarouselBinding
        initRow()
    }

    private fun initRow() {
        binding.run {
            data?.let { data ->

                if (!listAdapter.hasStableIds()) {
                    listAdapter.setHasStableIds(true)
                }

                // Item List
                data.itemList?.let {
                    this.viewPagerVertical.run {
                        adapter = listAdapter
                    }

                    // Indicator Dots
                    if (data.showIndicator == true) {
                        tabLayout.visible()
                        TabLayoutMediator(tabLayout, viewPagerVertical) { _, _ -> }.attach()
                    } else tabLayout.gone()

                    // Items
                    val itemList = data.itemType?.let {
                        data.itemList.getItemListByRowType(
                            rowType = it,
                            itemClickHandler = itemClickHandler,
                            isInCarousel = true,
                            isInList = false
                        )
                    }

                    listAdapter.submitList(itemList)
                } ?: run {
                    binding.root.gone()
                }

            }
        }
    }
}