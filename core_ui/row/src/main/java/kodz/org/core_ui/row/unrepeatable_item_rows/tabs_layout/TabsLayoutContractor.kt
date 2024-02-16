package kodz.org.core_ui.row.unrepeatable_item_rows.tabs_layout

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.contractor.BaseUnrepeatableItemRowContractor
import kodz.org.core.common.AppLog
import kodz.org.core_ui.row.common.MultipleTypeAdapter
import kodz.org.core_ui.row.databinding.RowTabsLayoutBinding

class TabsLayoutContractor() : BaseUnrepeatableItemRowContractor() {
    override var itemClickHandler: ItemClickHandler? = null
    override var viewBinding: ViewDataBinding? = null
    private var binding: RowTabsLayoutBinding? = null
    private val listAdapter by lazy { MultipleTypeAdapter() }

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        viewBinding = viewDataBinding
        binding = viewBinding as RowTabsLayoutBinding
        initRow()
    }

    private fun initRow() {
        binding?.run {
            data?.let { data ->

                if (!listAdapter.hasStableIds()) {
                    listAdapter.setHasStableIds(true)
                }

                // ViewPager
                tabsLayoutViewPager.adapter = listAdapter

                // Tabs
                data.tabs?.forEach { tab ->
                    tab.tabTitle?.let { tabText ->
                        tabsLayoutTabLayout.addTab(
                            tabsLayoutTabLayout.newTab().apply {
                                text = tabText
                                tab.tabIcon?.resourceId?.let { tabIcon ->
                                    setIcon(tabIcon)
                                }
                            }
                        )
                    }

                    tab.tabContent?.let { itemListJson ->
                        AppLog(itemListJson[0].toString())
                    }
                }

                // TabLayoutMediator(tabsLayoutTabLayout, tabsLayoutViewPager) { _, _ -> }.attach()
            }
        }
    }
}