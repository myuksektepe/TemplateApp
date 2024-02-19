package kodz.org.core_ui.row.unrepeatable_item_rows.tabs_layout

import android.content.ContextWrapper
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import com.google.android.material.tabs.TabLayoutMediator
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.contractor.BaseUnrepeatableItemRowContractor
import kodz.org.core.common.AppLog
import kodz.org.core.model.ItemClickEventModel
import kodz.org.core_ui.row.databinding.RowTabsLayoutBinding
import kodz.org.core_ui.row.unrepeatable_item_rows.tabs_layout.tab_page.TabsLayoutPage
import kodz.org.core_ui.row.unrepeatable_item_rows.tabs_layout.tab_page.TabsLayoutPageAdapter

class TabsLayoutContractor() : BaseUnrepeatableItemRowContractor() {
    override var viewBinding: ViewDataBinding? = null
    private var binding: RowTabsLayoutBinding? = null
    private var pageAdapter: TabsLayoutPageAdapter? = null
    private val tabTitles = mutableListOf<Pair<String, Int?>>()

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        viewBinding = viewDataBinding
        binding = viewBinding as RowTabsLayoutBinding
        initRow()
    }

    private fun initRow() {
        binding?.run {
            data?.let { data ->

                // ViewPager
                val context = (this.root.context as ContextWrapper).baseContext
                val fragmentManager = (context as FragmentActivity).supportFragmentManager

                tabsLayoutTabLayout.removeAllTabs()
                tabsLayoutViewPager.isUserInputEnabled = false
                pageAdapter = TabsLayoutPageAdapter(fragmentManager = fragmentManager, context.lifecycle)

                // Tabs
                data.tabs?.forEach { tab ->
                    tab.tabTitle?.let { tabText ->
                        tabTitles.add(tabText to tab.tabIcon?.resourceId)
                        /*
                        tabsLayoutTabLayout.addTab(
                            tabsLayoutTabLayout.newTab().apply {
                                text = tabText
                                tab.tabIcon?.resourceId?.let { tabIcon ->
                                    setIcon(tabIcon)
                                }
                            }
                        )
                         */
                    }

                    tab.tabContent?.let { itemListJson ->
                        pageAdapter?.addFragment(TabsLayoutPage(itemListJson))
                    }
                }

                tabsLayoutViewPager.adapter = pageAdapter

                TabLayoutMediator(tabsLayoutTabLayout, tabsLayoutViewPager) { tab, position ->
                    tab.text = tabTitles[position].first
                    tabTitles[position].second?.let { tab.setIcon(it) }
                }.attach()
            }
        }
    }

    override var itemClickHandler: ItemClickHandler? = object : ItemClickHandler {
        override fun onItemClick(itemClickEventModel: ItemClickEventModel?) {
            itemClickEventModel?.let {
                AppLog(it.toString())
            }
        }
    }
}

