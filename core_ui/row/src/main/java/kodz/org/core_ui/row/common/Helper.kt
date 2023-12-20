package kodz.org.core_ui.row.common

import com.google.gson.Gson
import com.google.gson.JsonObject
import kodz.org.core.base.data.http.toResponseModel
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.BaseRow
import kodz.org.core.base.row.BaseRowContractor
import kodz.org.core.base.row.BaseRowDataModel
import kodz.org.core_ui.row.carousel_item.CarouselItemRow
import kodz.org.core_ui.row.carousel_item.CarouselItemRowDataModel
import kodz.org.core_ui.row.entry_item_1.EntryItem1Row
import kodz.org.core_ui.row.entry_item_1.EntryItem1RowDataModel
import kodz.org.core_ui.row.entry_item_2.EntryItem2Row
import kodz.org.core_ui.row.entry_item_2.EntryItem2RowDataModel
import kodz.org.core_ui.row.quote.QuoteRow
import kodz.org.core_ui.row.quote.QuoteRowDataModel
import kotlin.reflect.full.primaryConstructor

fun List<JsonObject>.getItemListByRowType(
    rowType: String,
    itemClickHandler: ItemClickHandler? = null,
    isInSlider: Boolean
): List<BaseRow> {
    val itemList = mutableListOf<BaseRow>()
    when (rowType) {
        "CarouselItemRow" -> {
            this.forEach {
                Gson().fromJson(it, CarouselItemRowDataModel::class.java)?.run {
                    if (!this.title.isNullOrEmpty() || !this.imageUrl.isNullOrEmpty()) {
                        itemList.add(
                            CarouselItemRow(this, isInSlider).apply {
                                contractor.itemClickHandler = itemClickHandler
                            }
                        )
                    }
                }
            }
        }

        "QuoteRow" -> {
            this.forEach {
                Gson().fromJson(it, QuoteRowDataModel::class.java)?.run {
                    if (!this.text.isNullOrEmpty()) {
                        itemList.add(
                            QuoteRow(this, isInSlider).apply {
                                contractor.itemClickHandler = itemClickHandler
                            }
                        )
                    }
                }
            }
        }

        "EntryItem1Row" -> {
            this.forEach {
                Gson().fromJson(it, EntryItem1RowDataModel::class.java)?.run {
                    if (!this.title.isNullOrEmpty()) {
                        itemList.add(
                            EntryItem1Row(this, isInSlider).apply {
                                contractor.itemClickHandler = itemClickHandler
                            }
                        )
                    }
                }
            }
        }

        "EntryItem2Row" -> {
            this.forEach {
                Gson().fromJson(it, EntryItem2RowDataModel::class.java)?.run {
                    if (!this.title.isNullOrEmpty()) {
                        itemList.add(
                            EntryItem2Row(this, isInSlider).apply {
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

inline fun <reified R : BaseRow, reified C : BaseRowContractor, reified BDM : BaseRowDataModel> makeRow(
    dataModelString: String?,
    itemClickHandler: ItemClickHandler? = null
): BaseRow? {
    return dataModelString?.toResponseModel<BDM>()?.let { dataModel ->
        return R::class.primaryConstructor?.call(dataModel, null)?.apply {
            (contractor as? C)?.itemClickHandler = itemClickHandler
        }
    }
}

inline fun <reified R : BaseRow, reified C : BaseRowContractor> makeRow(
    dataModel: BaseRowDataModel?,
    itemClickHandler: ItemClickHandler? = null
): BaseRow? {
    return R::class.primaryConstructor?.call(dataModel)?.apply {
        (contractor as? C)?.itemClickHandler = itemClickHandler
    }
}