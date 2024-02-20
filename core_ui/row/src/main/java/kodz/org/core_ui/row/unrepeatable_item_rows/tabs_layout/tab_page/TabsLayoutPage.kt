package kodz.org.core_ui.row.unrepeatable_item_rows.tabs_layout.tab_page

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kodz.org.core.base.fragment.BaseFragment
import kodz.org.core.base.row.row.BaseRow
import kodz.org.core.base.viewmodel.BaseViewModel
import kodz.org.core.model.RowItemModel
import kodz.org.core_ui.row.R
import kodz.org.core_ui.row.common.MultipleTypeAdapter
import kodz.org.core_ui.row.common.convertRow
import kodz.org.core_ui.row.databinding.RowTabsLayoutPageBinding

class TabsLayoutPage(
    private val itemListJson: List<RowItemModel?>? = null
) : BaseFragment<BaseViewModel, RowTabsLayoutPageBinding>(R.layout.row_tabs_layout_page) {
    override val viewModel: BaseViewModel by viewModels()
    private val rowAdapter = MultipleTypeAdapter()
    private val componentList = mutableListOf<BaseRow>()

    override fun observeViewModel() {
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun viewDidLoad(savedInstanceState: Bundle?) {
        componentList.clear()
        binding.run {
            recyclerViewTabPage.run {
                // setItemViewCacheSize(rowAdapter.itemCount)
                rowAdapter.submitList(null)
                adapter = rowAdapter
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }

            itemListJson?.filterNotNull()?.forEachIndexed { index, rowItem ->
                if (rowItem.isVisible == true) {
                    rowItem.rowName?.convertRow(
                        dataModelJsonObject = rowItem.dataModel,
                        itemClickHandler = null
                    )?.let { row -> componentList.add(row) }
                }
            }

            if (componentList.isNotEmpty()) {
                rowAdapter.submitList(componentList)
                recyclerViewTabPage.setItemViewCacheSize(componentList.size)
            }

        }
    }

    override fun bindingViewModel(binding: RowTabsLayoutPageBinding) {
    }

    override val isBottomNavigationViewVisible: Boolean = false

    override fun onBackPressed(): Boolean = true
}