package kodz.org.core_ui.row.list_rows.vertical_list

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.contractor.BaseListRowContractor
import kodz.org.core.base.row.row.BaseRow
import kodz.org.core.common.SpacesItemDecoration
import kodz.org.core.common.consts.ONE
import kodz.org.core.common.consts.ZERO
import kodz.org.core.extension.gone
import kodz.org.core.extension.setSpamProtectedClickListener
import kodz.org.core.extension.subSafeList
import kodz.org.core.extension.visible
import kodz.org.core_ui.row.common.MultipleTypeAdapter
import kodz.org.core_ui.row.common.getItemListByRowType
import kodz.org.core_ui.row.databinding.RowVerticalListBinding

class VerticalListRowContractor : BaseListRowContractor() {
    override var viewBinding: ViewDataBinding? = null
    lateinit var binding: RowVerticalListBinding
    override var itemClickHandler: ItemClickHandler? = null
    private val listAdapter by lazy { MultipleTypeAdapter() }
    private var showingItemCount = ZERO
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

                        val context = this.context
                        val itemSpaceInt = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._8sdp)

                        // Remove item decoration first
                        if (itemDecorationCount > ZERO) removeItemDecorationAt(ZERO)

                        // Grid or Staggered
                        if (data.listType == ListType.STAGGERED) {
                            layoutManager = StaggeredGridLayoutManager(
                                data.columnCount ?: ONE,
                                LinearLayoutManager.VERTICAL
                            ).apply {
                                gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
                            }
                        } else {
                            layoutManager = GridLayoutManager(context, data.columnCount ?: ONE)
                        }

                        // Add item decoration
                        addItemDecoration(SpacesItemDecoration(itemSpaceInt))

                        // Set listAdapter
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
                    checkShowMoreButtonVisibility(data.itemList.size)

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
            val itemFilteredList = data.itemCount?.let {
                if (showingItemCount == ZERO) showingItemCount = it
                list.subSafeList(fromIndex = ZERO, toIndex = (showingItemCount + increase))
            } ?: list
            showingItemCount += increase
            checkShowMoreButtonVisibility(list.size)

            data.itemType?.let {
                itemFilteredList.getItemListByRowType(
                    rowType = it,
                    itemClickHandler = itemClickHandler,
                    isInSlider = false,
                    isInList = true
                )
            }?.let {
                return it
            }

        } ?: run {
            return listOf()
        }
    }

    private fun checkShowMoreButtonVisibility(itemListSize: Int) =
        if (showingItemCount >= itemListSize) binding.btnShowMore.gone() else binding.btnShowMore.visible()

}