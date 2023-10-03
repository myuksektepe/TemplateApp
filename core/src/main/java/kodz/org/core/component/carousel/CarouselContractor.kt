package kodz.org.core.component.carousel

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.viewpager2.widget.ViewPager2
import kodz.org.core.R
import kodz.org.core.base.adapter.MultipleTypeAdapter
import kodz.org.core.base.component.ComponentBaseContractor
import kodz.org.core.base.component.ComponentBaseRow
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.common.HorizontalMarginItemDecoration
import kodz.org.core.component.carousel_item.CarouselItemRow
import kodz.org.core.databinding.ComponentCarouselBinding
import kodz.org.core.extension.gone

class CarouselContractor : ComponentBaseContractor() {
    override var binding: ViewDataBinding? = null
    private val carouselAdapter by lazy { MultipleTypeAdapter() }
    override var eventHandler: ItemClickHandler? = null

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        binding = viewDataBinding
        initComponent()
    }

    private fun initComponent() {
        (binding as? ComponentCarouselBinding)?.run {
            data?.itemList?.let { list ->
                prepareCarousel(this.viewPagerVertical)
                this.viewPagerVertical.adapter = carouselAdapter

                val itemList = mutableListOf<CarouselItemRow>()
                list.forEach {
                    itemList.add(
                        CarouselItemRow(it).apply {
                            contractor.eventHandler = eventHandler
                        }
                    )
                }

                carouselAdapter.submitList(itemList as List<ComponentBaseRow>?)
            } ?: run {
                binding?.root?.gone()
            }
        }
    }

    private fun prepareCarousel(viewPager: ViewPager2) {
        val context = viewPager.context
        viewPager.offscreenPageLimit = 1
        val nextItemVisiblePx = context.resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx = context.resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            // Next line scales the item's height. You can remove it if you don't want this effect
            page.scaleY = 1 - (0.25f * kotlin.math.abs(position))
            // If you want a fading effect uncomment the next line:
            page.alpha = 0.47f + (1 - kotlin.math.abs(position))
        }
        viewPager.setPageTransformer(pageTransformer)
        // The ItemDecoration gives the current (centered) item horizontal margin so that
        // it doesn't occupy the whole screen width. Without it the items overlap
        val itemDecoration = HorizontalMarginItemDecoration(
            context,
            R.dimen.viewpager_current_item_horizontal_margin
        )
        if (viewPager.itemDecorationCount == 0) viewPager.addItemDecoration(itemDecoration)
    }
}