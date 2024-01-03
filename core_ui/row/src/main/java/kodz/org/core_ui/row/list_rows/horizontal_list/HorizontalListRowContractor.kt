package kodz.org.core_ui.row.list_rows.horizontal_list

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.contractor.BaseListRowContractor
import kodz.org.core.base.row.row.BaseRow
import kodz.org.core.common.SpacesItemDecoration
import kodz.org.core.common.consts.ZERO
import kodz.org.core.extension.gone
import kodz.org.core_ui.row.common.MultipleTypeAdapter
import kodz.org.core_ui.row.common.getItemListByRowType
import kodz.org.core_ui.row.databinding.RowHorizontalListBinding

/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 31.10.2023.
 */
class HorizontalListRowContractor : BaseListRowContractor() {
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
                data.itemList?.let {
                    this.rcyHorizontalList.run {

                        val context = this.context
                        val itemSpaceInt = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._8sdp)
                        setItemViewCacheSize(listAdapter.itemCount)

                        // Remove item decoration first
                        if (itemDecorationCount > ZERO) removeItemDecorationAt(ZERO)

                        // Layout Manager
                        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

                        // Add item decoration
                        addItemDecoration(SpacesItemDecoration(itemSpaceInt))

                        // Set listAdapter
                        adapter = listAdapter
                    }

                    // Items
                    val itemList = data.itemType?.let {
                        data.itemList.getItemListByRowType(
                            rowType = it,
                            itemClickHandler = itemClickHandler,
                            isInCarousel = true,
                            isInList = false
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