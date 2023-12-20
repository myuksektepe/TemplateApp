package kodz.org.feature.screen.domain.adapter

import android.widget.Filter
import android.widget.Filterable
import kodz.org.core.base.row.BaseRow
import kodz.org.core_ui.row.common.MultipleTypeAdapter
import kodz.org.core_ui.row.entry_item_1.EntryItem1Row
import kodz.org.core_ui.row.entry_item_1.EntryItem1RowDataModel
import kodz.org.core_ui.row.entry_item_2.EntryItem2Row
import kodz.org.core_ui.row.entry_item_2.EntryItem2RowDataModel

class ScreenAdapter : MultipleTypeAdapter(), Filterable {
    override fun getFilter(): Filter = customFilter
    private var list = mutableListOf<BaseRow>()

    fun submitData(rowList: MutableList<BaseRow?>?) {
        rowList?.let {
            this.list = it.filterNotNull().toMutableList()
            submitList(list)
        }
    }

    private val customFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = mutableListOf<BaseRow>()
            if (constraint.isNullOrEmpty()) {
                filteredList.addAll(list)
            } else {
                for (item in list) {

                    // EntryItem1Row
                    if (item::class == EntryItem1Row::class) {
                        (item.dataModel as EntryItem1RowDataModel).let {
                            if (it.title?.lowercase()?.contains(constraint.toString().lowercase()) == true) {
                                filteredList.add(item)
                            }
                        }

                        // EntryItem2Row
                    } else if (item::class == EntryItem2Row::class) {
                        (item.dataModel as EntryItem2RowDataModel).let {
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
                val list = it as MutableList<BaseRow>
                submitList(list)
            }
        }

    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }
}