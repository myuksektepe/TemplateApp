package kodz.org.core_ui.row.vertical_list

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.BaseRow
import kodz.org.core.base.row.BaseRowContractor
import kodz.org.core.common.ZERO
import kodz.org.core.extension.gone
import kodz.org.core.extension.setSpamProtectedClickListener
import kodz.org.core.extension.subSafeList
import kodz.org.core.extension.visible
import kodz.org.core_ui.row.common.MultipleTypeAdapter
import kodz.org.core_ui.row.common.getItemListByRowType
import kodz.org.core_ui.row.databinding.RowVerticalListBinding

class VerticalListRowContractor : BaseRowContractor() {
    override var viewBinding: ViewDataBinding? = null
    lateinit var binding: RowVerticalListBinding
    override var itemClickHandler: ItemClickHandler? = null
    private val listAdapter by lazy { MultipleTypeAdapter() }
    override fun initBinding(viewDataBinding: ViewDataBinding) {
        viewBinding = viewDataBinding
        binding = viewDataBinding as RowVerticalListBinding
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

                    this.rcyVerticalList.run {
                        layoutManager = GridLayoutManager(this.context, data.columnCount ?: 1)
                        adapter = listAdapter
                    }

                    // Items
                    var itemList = getSubList(data, ZERO)

                    // Show More
                    if (data.isShowMoreButtonVisible == true) btnShowMore.visible() else btnShowMore.gone()
                    btnShowMore.setSpamProtectedClickListener {
                        data.showMoreItemCount?.let {
                            itemList = getSubList(data, it)
                            listAdapter.submitList(itemList)
                        }
                    }

                    listAdapter.submitList(itemList)
                    root.visible()

                } ?: run {
                    root.gone()
                }

            }
        }
    }

    private fun getSubList(data: VerticalListRowDataModel, increase: Int = ZERO): List<BaseRow> {
        data.itemList?.let { list ->
            val itemList = data.itemCount?.let {
                list.subSafeList(fromIndex = ZERO, toIndex = (it + increase))
            } ?: list

            if (itemList.size >= data.itemList.size) binding?.btnShowMore?.gone()

            data.itemType?.let {
                itemList.getItemListByRowType(
                    rowType = it,
                    itemClickHandler = itemClickHandler,
                    isInSlider = false
                )
            }?.let {
                return it
            }

        } ?: run {
            return listOf()
        }
    }
}