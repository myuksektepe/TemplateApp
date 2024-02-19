package kodz.org.core_ui.row.unrepeatable_item_rows.tabs_layout.tab_page

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


class TabsLayoutPageAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    private val fragmentList: ArrayList<Fragment> = ArrayList<Fragment>()
    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    fun addFragment(fragment: Fragment) {
        fragmentList.add(fragment)
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }
}