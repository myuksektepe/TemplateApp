package kodz.org.core_ui.row.vertical_list

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.BaseRowContractor
import kodz.org.core.extension.gone
import kodz.org.core_ui.row.common.MultipleTypeAdapter
import kodz.org.core_ui.row.common.getItemListByRowType
import kodz.org.core_ui.row.databinding.RowVerticalListBinding

class VerticalListRowContractor : BaseRowContractor() {
    override var itemClickHandler: ItemClickHandler? = null
    override var binding: ViewDataBinding? = null
    private val listAdapter by lazy { MultipleTypeAdapter() }
    override fun initBinding(viewDataBinding: ViewDataBinding) {
        binding = viewDataBinding
        initRow()
    }

    private fun initRow() {
        (binding as? RowVerticalListBinding)?.run {
            data?.let { data ->

                if (!listAdapter.hasStableIds()) {
                    listAdapter.setHasStableIds(true)
                }

                // Item List
                data.itemList?.let {

                    this.rcyVerticalList.run {
                        layoutManager = GridLayoutManager(this.context, data.columnCount ?: 1)
                        // layoutManager = StaggeredGridLayoutManager(data.columnCount ?: 1, StaggeredGridLayoutManager.VERTICAL)
                        adapter = listAdapter
                    }

                    val itemList = data.itemType?.let { data.itemList.getItemListByRowType(it, itemClickHandler, false) }
                    listAdapter.submitList(itemList)

                } ?: run {
                    binding?.root?.gone()
                }

            }
        }
    }
}