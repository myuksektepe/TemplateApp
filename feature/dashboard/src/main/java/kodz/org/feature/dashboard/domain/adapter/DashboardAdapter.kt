package kodz.org.feature.dashboard.domain.adapter

import android.widget.Filter
import android.widget.Filterable
import kodz.org.core.base.adapter.MultipleTypeAdapter
import kodz.org.core.base.component.ComponentBaseRow
import kodz.org.core.component.entry_item_1.EntryItem1DataModel
import kodz.org.core.component.entry_item_1.EntryItem1Row
import kodz.org.core.component.entry_item_2.EntryItem2DataModel
import kodz.org.core.component.entry_item_2.EntryItem2Row

class DashboardAdapter : MultipleTypeAdapter(), Filterable {
    override fun getFilter(): Filter = customFilter
    private var list = mutableListOf<ComponentBaseRow>()

    fun submitData(rowList: MutableList<ComponentBaseRow?>?) {
        rowList?.let {
            this.list = it.filterNotNull().toMutableList()
            submitList(list)
        }
    }

    private val customFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = mutableListOf<ComponentBaseRow>()
            if (constraint.isNullOrEmpty()) {
                filteredList.addAll(list)
            } else {
                for (item in list) {

                    // EntryItem1Row
                    if (item::class == EntryItem1Row::class) {
                        (item.dataModel as EntryItem1DataModel).let {
                            if (it.title?.lowercase()?.contains(constraint.toString().lowercase()) == true) {
                                filteredList.add(item)
                            }
                        }

                        // EntryItem2Row
                    } else if (item::class == EntryItem2Row::class) {
                        (item.dataModel as EntryItem2DataModel).let {
                            if (it.title?.lowercase()?.startsWith(constraint.toString().lowercase()) == true) {
                                filteredList.add(item)
                            }
                        }
                    }

                }
            }

            val result = FilterResults()
            result.values = filteredList
            return result
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            results?.values?.let {
                val list = it as MutableList<ComponentBaseRow>
                submitList(list)
            }
        }
    }
}