package kodz.org.core_ui.row.slider

import androidx.databinding.ViewDataBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.BaseRow
import kodz.org.core.base.row.BaseRowContractor
import kodz.org.core.extension.gone
import kodz.org.core.extension.makeSlidable
import kodz.org.core.extension.visible
import kodz.org.core_ui.row.carousel.carousel_item.CarouselItemRow
import kodz.org.core_ui.row.carousel.carousel_item.CarouselItemRowDataModel
import kodz.org.core_ui.row.common.MultipleTypeAdapter
import kodz.org.core_ui.row.databinding.RowSliderBinding
import kodz.org.core_ui.row.quote.QuoteDataModel
import kodz.org.core_ui.row.quote.QuoteRow


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 1.11.2023.
 */
class SliderRowContractor : BaseRowContractor() {
    override var itemClickHandler: ItemClickHandler? = null
    override var binding: ViewDataBinding? = null
    private val sliderAdapter by lazy { MultipleTypeAdapter() }

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        binding = viewDataBinding
        initRow()
    }

    private fun initRow() {
        (binding as? RowSliderBinding)?.run {
            data?.let { data ->

                // Item List
                data.itemList?.let {
                    viewPagerVertical.adapter = sliderAdapter

                    // Indicator Dots
                    if (data.showIndicator == true) {
                        tabLayout.visible()
                        TabLayoutMediator(tabLayout, viewPagerVertical) { _, _ -> }.attach()
                    } else tabLayout.gone()

                    // Items
                    val itemList = mutableListOf<BaseRow>()

                    when (data.itemType) {
                        "CarouselItemRow" -> {
                            data.itemList.forEach {
                                Gson().fromJson(it, CarouselItemRowDataModel::class.java)?.run {
                                    if (!this.title.isNullOrEmpty() || !this.imageUrl.isNullOrEmpty()) {
                                        itemList.add(
                                            CarouselItemRow(this).apply {
                                                binding?.makeSlidable()
                                            }
                                        )
                                    }
                                }
                            }
                        }

                        "QuoteRow" -> {
                            data.itemList.forEach {
                                Gson().fromJson(it, QuoteDataModel::class.java)?.run {
                                    if (!this.text.isNullOrEmpty()) {
                                        itemList.add(
                                            QuoteRow(this).apply {
                                                binding?.makeSlidable()
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }

                    sliderAdapter.submitList(itemList as List<BaseRow>?)
                } ?: kotlin.run {
                    binding?.root?.gone()
                }

            }
        }
    }
}