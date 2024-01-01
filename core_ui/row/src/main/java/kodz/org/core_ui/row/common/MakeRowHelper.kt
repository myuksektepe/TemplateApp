package kodz.org.core_ui.row.common

import kodz.org.core.base.data.http.toResponseModel
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.contractor.BaseItemRowContractor
import kodz.org.core.base.row.contractor.BaseListRowContractor
import kodz.org.core.base.row.contractor.BaseUnrepeatableItemRowContractor
import kodz.org.core.base.row.datamodel.BaseItemRowDataModel
import kodz.org.core.base.row.datamodel.BaseListRowDataModel
import kodz.org.core.base.row.datamodel.BaseUnrepeatableItemRowDataModel
import kodz.org.core.base.row.row.BaseItemRow
import kodz.org.core.base.row.row.BaseListRow
import kodz.org.core.base.row.row.BaseRow
import kodz.org.core.base.row.row.BaseUnrepeatableItemRow
import kodz.org.core.common.AppLog
import kodz.org.core_ui.row.item_rows.box.BoxRow
import kodz.org.core_ui.row.item_rows.box.BoxRowContractor
import kodz.org.core_ui.row.item_rows.box.BoxRowRowDataModel
import kodz.org.core_ui.row.item_rows.entry_item_1.EntryItem1Row
import kodz.org.core_ui.row.item_rows.entry_item_1.EntryItem1RowContractor
import kodz.org.core_ui.row.item_rows.entry_item_1.EntryItem1RowRowDataModel
import kodz.org.core_ui.row.item_rows.entry_item_2.EntryItem2Row
import kodz.org.core_ui.row.item_rows.entry_item_2.EntryItem2RowContractor
import kodz.org.core_ui.row.item_rows.entry_item_2.EntryItem2RowRowDataModel
import kodz.org.core_ui.row.item_rows.full_width_image.FullWidthImageRow
import kodz.org.core_ui.row.item_rows.full_width_image.FullWidthImageRowContractor
import kodz.org.core_ui.row.item_rows.full_width_image.FullWidthImageRowRowDataModel
import kodz.org.core_ui.row.item_rows.long_text.LongTextRow
import kodz.org.core_ui.row.item_rows.long_text.LongTextRowContractor
import kodz.org.core_ui.row.item_rows.long_text.LongTextRowRowDataModel
import kodz.org.core_ui.row.item_rows.quote.QuoteRow
import kodz.org.core_ui.row.item_rows.quote.QuoteRowContractor
import kodz.org.core_ui.row.item_rows.quote.QuoteRowRowDataModel
import kodz.org.core_ui.row.item_rows.video_player.VideoPlayerRow
import kodz.org.core_ui.row.item_rows.video_player.VideoPlayerRowContractor
import kodz.org.core_ui.row.item_rows.video_player.VideoPlayerRowRowDataModel
import kodz.org.core_ui.row.item_rows.webview.WebViewRow
import kodz.org.core_ui.row.item_rows.webview.WebViewRowContractor
import kodz.org.core_ui.row.item_rows.webview.WebViewRowRowDataModel
import kodz.org.core_ui.row.list_rows.carousel.CarouselRow
import kodz.org.core_ui.row.list_rows.carousel.CarouselRowContractor
import kodz.org.core_ui.row.list_rows.carousel.CarouselRowDataModel
import kodz.org.core_ui.row.list_rows.horizontal_list.HorizontalListRow
import kodz.org.core_ui.row.list_rows.horizontal_list.HorizontalListRowContractor
import kodz.org.core_ui.row.list_rows.horizontal_list.HorizontalListRowDataModel
import kodz.org.core_ui.row.list_rows.vertical_list.VerticalListRow
import kodz.org.core_ui.row.list_rows.vertical_list.VerticalListRowContractor
import kodz.org.core_ui.row.list_rows.vertical_list.VerticalListRowDataModel
import kodz.org.core_ui.row.unrepeatable_item_rows.entry_title_1.EntryTitle1Row
import kodz.org.core_ui.row.unrepeatable_item_rows.entry_title_1.EntryTitle1RowContractor
import kodz.org.core_ui.row.unrepeatable_item_rows.entry_title_1.EntryTitle1RowRowDataModel
import kodz.org.core_ui.row.unrepeatable_item_rows.search_box.SearchBoxRow
import kodz.org.core_ui.row.unrepeatable_item_rows.search_box.SearchBoxRowContractor
import kodz.org.core_ui.row.unrepeatable_item_rows.search_box.SearchBoxRowDataModel
import kodz.org.core_ui.row.unrepeatable_item_rows.section_title.SectionTitleRow
import kodz.org.core_ui.row.unrepeatable_item_rows.section_title.SectionTitleRowContractor
import kodz.org.core_ui.row.unrepeatable_item_rows.section_title.SectionTitleRowDataModel
import kotlin.reflect.full.primaryConstructor

fun String.convertRow(
    dataModelString: String?,
    itemClickHandler: ItemClickHandler
): BaseRow? {
    var clsRow: BaseRow? = null
    when (this) {

        // List / Group
        "HorizontalListRow" -> {
            clsRow =
                makeListRow<HorizontalListRow, HorizontalListRowContractor, HorizontalListRowDataModel>(
                    dataModelString,
                    itemClickHandler
                )
        }

        "VerticalListRow" -> {
            clsRow =
                makeListRow<VerticalListRow, VerticalListRowContractor, VerticalListRowDataModel>(
                    dataModelString,
                    itemClickHandler
                )
        }

        "CarouselRow" -> {
            clsRow =
                makeListRow<CarouselRow, CarouselRowContractor, CarouselRowDataModel>(
                    dataModelString,
                    itemClickHandler
                )
        }

        // ========================================

        // Item Row (Repeatable)

        "EntryItem1Row" -> {
            clsRow =
                makeItemRow<EntryItem1Row, EntryItem1RowContractor, EntryItem1RowRowDataModel>(
                    dataModelString,
                    itemClickHandler
                )
        }

        "EntryItem2Row" -> {
            clsRow =
                makeItemRow<EntryItem2Row, EntryItem2RowContractor, EntryItem2RowRowDataModel>(
                    dataModelString,
                    itemClickHandler
                )
        }

        "FullWidthImageRow" -> {
            clsRow =
                makeItemRow<FullWidthImageRow, FullWidthImageRowContractor, FullWidthImageRowRowDataModel>(
                    dataModelString,
                    itemClickHandler
                )
        }

        "QuoteRow" -> {
            clsRow =
                makeItemRow<QuoteRow, QuoteRowContractor, QuoteRowRowDataModel>(
                    dataModelString,
                    itemClickHandler
                )
        }

        "WebViewRow" -> {
            clsRow =
                makeItemRow<WebViewRow, WebViewRowContractor, WebViewRowRowDataModel>(
                    dataModelString
                )
        }

        "LongTextRow" -> {
            clsRow =
                makeItemRow<LongTextRow, LongTextRowContractor, LongTextRowRowDataModel>(
                    dataModelString
                )
        }

        "VideoPlayerRow" -> {
            clsRow =
                makeItemRow<VideoPlayerRow, VideoPlayerRowContractor, VideoPlayerRowRowDataModel>(
                    dataModelString
                )
        }

        "BoxRow" -> {
            clsRow =
                makeItemRow<BoxRow, BoxRowContractor, BoxRowRowDataModel>(
                    dataModelString
                )
        }

        // ========================================

        // Item Row (Unrepeatable)
        "SectionTitleRow" -> {
            clsRow =
                makeUnrepeatableItemRow<SectionTitleRow, SectionTitleRowContractor, SectionTitleRowDataModel>(
                    dataModelString,
                    itemClickHandler
                )
        }

        "SearchBoxRow" -> {
            clsRow =
                makeUnrepeatableItemRow<SearchBoxRow, SearchBoxRowContractor, SearchBoxRowDataModel>(
                    dataModelString
                )
        }

        "EntryTitle1Row" -> {
            clsRow =
                makeUnrepeatableItemRow<EntryTitle1Row, EntryTitle1RowContractor, EntryTitle1RowRowDataModel>(
                    dataModelString
                )
        }
        else -> {
            clsRow = null
            AppLog("$this row hasn't been included in the app!")
        }
    }

    return clsRow
}


inline fun <reified R : BaseItemRow, reified C : BaseItemRowContractor, reified BDM : BaseItemRowDataModel> makeItemRow(
    dataModelString: String?,
    itemClickHandler: ItemClickHandler? = null
): BaseRow? {
    return dataModelString?.toResponseModel<BDM>()?.let { dataModel ->
        return R::class.primaryConstructor?.call(dataModel, null, null)?.apply {
            (contractor as? C)?.itemClickHandler = itemClickHandler
        }
    }
}

inline fun <reified R : BaseUnrepeatableItemRow, reified C : BaseUnrepeatableItemRowContractor, reified BDM : BaseUnrepeatableItemRowDataModel> makeUnrepeatableItemRow(
    dataModelString: String?,
    itemClickHandler: ItemClickHandler? = null
): BaseRow? {
    return dataModelString?.toResponseModel<BDM>()?.let { dataModel ->
        return R::class.primaryConstructor?.call(dataModel)?.apply {
            (contractor as? C)?.itemClickHandler = itemClickHandler
        }
    }
}

inline fun <reified R : BaseListRow, reified C : BaseListRowContractor, reified BDM : BaseListRowDataModel> makeListRow(
    dataModelString: String?,
    itemClickHandler: ItemClickHandler? = null
): BaseRow? {
    return dataModelString?.toResponseModel<BDM>()?.let { dataModel ->
        return R::class.primaryConstructor?.call(dataModel)?.apply {
            (contractor as? C)?.itemClickHandler = itemClickHandler
        }
    }
}

/*
inline fun <reified R : BaseRow, reified C : BaseRowContractor> makeRow(
    dataModel: BaseRowDataModel?,
    itemClickHandler: ItemClickHandler? = null
): BaseRow? {
    return R::class.primaryConstructor?.call(dataModel)?.apply {
        (contractor as? C)?.itemClickHandler = itemClickHandler
    }
}
 */