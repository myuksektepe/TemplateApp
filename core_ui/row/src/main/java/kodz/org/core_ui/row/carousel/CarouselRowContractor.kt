package kodz.org.core_ui.row.carousel

import androidx.databinding.ViewDataBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.BaseRow
import kodz.org.core.base.row.BaseRowContractor
import kodz.org.core.extension.gone
import kodz.org.core.extension.visible
import kodz.org.core_ui.row.carousel_item.CarouselItemRow
import kodz.org.core_ui.row.carousel_item.CarouselItemRowDataModel
import kodz.org.core_ui.row.common.MultipleTypeAdapter
import kodz.org.core_ui.row.databinding.RowCarouselBinding
import kodz.org.core_ui.row.entry_item_1.EntryItem1Row
import kodz.org.core_ui.row.entry_item_1.EntryItem1RowDataModel
import kodz.org.core_ui.row.entry_item_2.EntryItem2Row
import kodz.org.core_ui.row.entry_item_2.EntryItem2RowDataModel
import kodz.org.core_ui.row.quote.QuoteRow
import kodz.org.core_ui.row.quote.QuoteRowDataModel


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 1.11.2023.
 */
class CarouselRowContractor : BaseRowContractor() {
    override var itemClickHandler: ItemClickHandler? = null
    override var binding: ViewDataBinding? = null
    private val sliderAdapter by lazy { MultipleTypeAdapter() }

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        binding = viewDataBinding
        initRow()
    }

    private fun initRow() {
        (binding as? RowCarouselBinding)?.run {
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
                    val itemList = mutableListOf<BaseRow>()

                    when (data.itemType) {
                        "CarouselItemRow" -> {
                            data.itemList.forEach {
                                Gson().fromJson(it, CarouselItemRowDataModel::class.java)?.run {
                                    if (!this.title.isNullOrEmpty() || !this.imageUrl.isNullOrEmpty()) {
                                        itemList.add(
                                            CarouselItemRow(this, true).apply {
                                                contractor.itemClickHandler = itemClickHandler
                                            }
                                        )
                                    }
                                }
                            }
                        }

                        "QuoteRow" -> {
                            data.itemList.forEach {
                                Gson().fromJson(it, QuoteRowDataModel::class.java)?.run {
                                    if (!this.text.isNullOrEmpty()) {
                                        itemList.add(
                                            QuoteRow(this, true).apply {
                                                contractor.itemClickHandler = itemClickHandler
                                            }
                                        )
                                    }
                                }
                            }
                        }

                        "EntryItem1Row" -> {
                            data.itemList.forEach {
                                Gson().fromJson(it, EntryItem1RowDataModel::class.java)?.run {
                                    if (!this.title.isNullOrEmpty()) {
                                        itemList.add(
                                            EntryItem1Row(this, true).apply {
                                                contractor.itemClickHandler = itemClickHandler
                                            }
                                        )
                                    }
                                }
                            }
                        }

                        "EntryItem2Row" -> {
                            data.itemList.forEach {
                                Gson().fromJson(it, EntryItem2RowDataModel::class.java)?.run {
                                    if (!this.title.isNullOrEmpty()) {
                                        itemList.add(
                                            EntryItem2Row(this, true).apply {
                                                contractor.itemClickHandler = itemClickHandler
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