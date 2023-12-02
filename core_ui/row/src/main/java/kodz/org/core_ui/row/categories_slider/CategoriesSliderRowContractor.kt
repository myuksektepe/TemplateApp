package kodz.org.core_ui.row.categories_slider

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.BaseRow
import kodz.org.core.base.row.BaseRowContractor
import kodz.org.core.extension.gone
import kodz.org.core_ui.row.categories_slider.categories_slider_item.CategoriesSliderItemRow
import kodz.org.core_ui.row.common.MultipleTypeAdapter
import kodz.org.core_ui.row.databinding.RowCategoriesSliderBinding


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 31.10.2023.
 */
class CategoriesSliderRowContractor : BaseRowContractor() {
    override var itemClickHandler: ItemClickHandler? = null
    override var binding: ViewDataBinding? = null
    private val listAdapter by lazy { MultipleTypeAdapter() }

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        binding = viewDataBinding
        initRow()
    }

    private fun initRow() {
        (binding as? RowCategoriesSliderBinding)?.run {
            this.data?.let { data ->

                if (!listAdapter.hasStableIds()) {
                    listAdapter.setHasStableIds(true)
                }

                // Item List
                data.itemList?.let { list ->

                    this.rcyCategoriesList.run {
                        layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
                        adapter = listAdapter
                    }

                    val itemList = mutableListOf<CategoriesSliderItemRow>()
                    list.forEach {
                        itemList.add(
                            CategoriesSliderItemRow(it).apply {
                                contractor.itemClickHandler = itemClickHandler
                            }
                        )
                    }

                    listAdapter.submitList(itemList as List<BaseRow>?)
                } ?: run {
                    binding?.root?.gone()
                }

            }
        }
    }
}