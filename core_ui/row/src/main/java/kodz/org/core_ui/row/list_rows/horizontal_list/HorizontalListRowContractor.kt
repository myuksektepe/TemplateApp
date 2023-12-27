package kodz.org.core_ui.row.list_rows.horizontal_list

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.BaseRow
import kodz.org.core.base.row.BaseRowContractor
import kodz.org.core.extension.gone
import kodz.org.core_ui.row.common.MultipleTypeAdapter
import kodz.org.core_ui.row.databinding.RowHorizontalListBinding
import kodz.org.core_ui.row.item_rows.categories_slider_item.CategoriesSliderItemRow


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 31.10.2023.
 */
class HorizontalListRowContractor : BaseRowContractor() {
    override var viewBinding: ViewDataBinding? = null
    lateinit var binding: RowHorizontalListBinding
    override var itemClickHandler: ItemClickHandler? = null
    private val listAdapter by lazy { MultipleTypeAdapter() }

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        viewBinding = viewDataBinding
        binding = viewDataBinding as RowHorizontalListBinding
        initRow()
    }

    private fun initRow() {
        binding.run {
            this.data?.let { data ->

                if (!listAdapter.hasStableIds()) {
                    listAdapter.setHasStableIds(true)
                }

                // Item List
                data.itemList?.let { list ->

                    this.rcyHorizontalList.run {
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
                    binding.root.gone()
                }

            }
        }
    }
}