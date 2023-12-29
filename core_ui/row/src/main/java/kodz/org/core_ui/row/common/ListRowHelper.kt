package kodz.org.core_ui.row.common

import com.google.gson.Gson
import com.google.gson.JsonObject
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.row.BaseRow
import kodz.org.core_ui.row.item_rows.box.BoxRow
import kodz.org.core_ui.row.item_rows.box.BoxRowRowDataModel
import kodz.org.core_ui.row.item_rows.categories_slider_item.CategoriesSliderItemRow
import kodz.org.core_ui.row.item_rows.categories_slider_item.CategoriesSliderItemRowRowDataModel
import kodz.org.core_ui.row.item_rows.entry_item_1.EntryItem1Row
import kodz.org.core_ui.row.item_rows.entry_item_1.EntryItem1RowRowDataModel
import kodz.org.core_ui.row.item_rows.entry_item_2.EntryItem2Row
import kodz.org.core_ui.row.item_rows.entry_item_2.EntryItem2RowRowDataModel
import kodz.org.core_ui.row.item_rows.quote.QuoteRow
import kodz.org.core_ui.row.item_rows.quote.QuoteRowRowDataModel

fun List<JsonObject>.getItemListByRowType(
    rowType: String,
    itemClickHandler: ItemClickHandler? = null,
    isInSlider: Boolean,
    isInList: Boolean,
): List<BaseRow> {
    val itemList = mutableListOf<BaseRow>()
    when (rowType) {
        "BoxRow" -> {
            this.forEach {
                Gson().fromJson(it, BoxRowRowDataModel::class.java)?.run {
                    if (!this.title.isNullOrEmpty()) {
                        itemList.add(
                            BoxRow(this, isInSlider, isInList).apply {
                                contractor.itemClickHandler = itemClickHandler
                            }
                        )
                    }
                }
            }
        }

        "QuoteRow" -> {
            this.forEach {
                Gson().fromJson(it, QuoteRowRowDataModel::class.java)?.run {
                    if (!this.text.isNullOrEmpty()) {
                        itemList.add(
                            QuoteRow(this, isInSlider, isInList).apply {
                                contractor.itemClickHandler = itemClickHandler
                            }
                        )
                    }
                }
            }
        }

        "EntryItem1Row" -> {
            this.forEach {
                Gson().fromJson(it, EntryItem1RowRowDataModel::class.java)?.run {
                    if (!this.title.isNullOrEmpty()) {
                        itemList.add(
                            EntryItem1Row(this, isInSlider, isInList).apply {
                                contractor.itemClickHandler = itemClickHandler
                            }
                        )
                    }
                }
            }
        }

        "EntryItem2Row" -> {
            this.forEach {
                Gson().fromJson(it, EntryItem2RowRowDataModel::class.java)?.run {
                    if (!this.title.isNullOrEmpty()) {
                        itemList.add(
                            EntryItem2Row(this, isInSlider, isInList).apply {
                                contractor.itemClickHandler = itemClickHandler
                            }
                        )
                    }
                }
            }
        }

        "CategoriesSliderItemRow" -> {
            this.forEach {
                Gson().fromJson(it, CategoriesSliderItemRowRowDataModel::class.java)?.run {
                    if (!this.categoryName.isNullOrEmpty()) {
                        itemList.add(
                            CategoriesSliderItemRow(this, isInSlider, isInList).apply {
                                contractor.itemClickHandler = itemClickHandler
                            }
                        )
                    }
                }
            }
        }
    }
    return itemList
}
