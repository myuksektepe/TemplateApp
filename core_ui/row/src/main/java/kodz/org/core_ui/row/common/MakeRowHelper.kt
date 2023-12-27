package kodz.org.core_ui.row.common

import kodz.org.core.base.data.http.toResponseModel
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.BaseRow
import kodz.org.core.base.row.BaseRowContractor
import kodz.org.core.base.row.BaseRowDataModel
import kodz.org.core.common.AppLog
import kodz.org.core_ui.row.item_rows.box.BoxRow
import kodz.org.core_ui.row.item_rows.box.BoxRowContractor
import kodz.org.core_ui.row.item_rows.box.BoxRowDataModel
import kodz.org.core_ui.row.item_rows.entry_item_1.EntryItem1Row
import kodz.org.core_ui.row.item_rows.entry_item_1.EntryItem1RowContractor
import kodz.org.core_ui.row.item_rows.entry_item_1.EntryItem1RowDataModel
import kodz.org.core_ui.row.item_rows.entry_item_2.EntryItem2Row
import kodz.org.core_ui.row.item_rows.entry_item_2.EntryItem2RowContractor
import kodz.org.core_ui.row.item_rows.entry_item_2.EntryItem2RowDataModel
import kodz.org.core_ui.row.item_rows.entry_title_1.EntryTitle1Row
import kodz.org.core_ui.row.item_rows.entry_title_1.EntryTitle1RowContractor
import kodz.org.core_ui.row.item_rows.entry_title_1.EntryTitle1RowDataModel
import kodz.org.core_ui.row.item_rows.full_width_image.FullWidthImageRow
import kodz.org.core_ui.row.item_rows.full_width_image.FullWidthImageRowContractor
import kodz.org.core_ui.row.item_rows.full_width_image.FullWidthImageRowDataModel
import kodz.org.core_ui.row.item_rows.long_text.LongTextRow
import kodz.org.core_ui.row.item_rows.long_text.LongTextRowContractor
import kodz.org.core_ui.row.item_rows.long_text.LongTextRowDataModel
import kodz.org.core_ui.row.item_rows.quote.QuoteRow
import kodz.org.core_ui.row.item_rows.quote.QuoteRowContractor
import kodz.org.core_ui.row.item_rows.quote.QuoteRowDataModel
import kodz.org.core_ui.row.item_rows.video_player.VideoPlayerRow
import kodz.org.core_ui.row.item_rows.video_player.VideoPlayerRowContractor
import kodz.org.core_ui.row.item_rows.video_player.VideoPlayerRowDataModel
import kodz.org.core_ui.row.item_rows.webview.WebViewRow
import kodz.org.core_ui.row.item_rows.webview.WebViewRowContractor
import kodz.org.core_ui.row.item_rows.webview.WebViewRowDataModel
import kodz.org.core_ui.row.list_rows.carousel.CarouselRow
import kodz.org.core_ui.row.list_rows.carousel.CarouselRowContractor
import kodz.org.core_ui.row.list_rows.carousel.CarouselRowDataModel
import kodz.org.core_ui.row.list_rows.horizontal_list.HorizontalListRow
import kodz.org.core_ui.row.list_rows.horizontal_list.HorizontalListRowContractor
import kodz.org.core_ui.row.list_rows.horizontal_list.HorizontalListRowDataModel
import kodz.org.core_ui.row.list_rows.vertical_list.VerticalListRow
import kodz.org.core_ui.row.list_rows.vertical_list.VerticalListRowContractor
import kodz.org.core_ui.row.list_rows.vertical_list.VerticalListRowDataModel
import kodz.org.core_ui.row.unrepeatable_rows.search_box.SearchBoxRow
import kodz.org.core_ui.row.unrepeatable_rows.search_box.SearchBoxRowContractor
import kodz.org.core_ui.row.unrepeatable_rows.search_box.SearchBoxRowDataModel
import kodz.org.core_ui.row.unrepeatable_rows.section_title.SectionTitleRow
import kodz.org.core_ui.row.unrepeatable_rows.section_title.SectionTitleRowContractor
import kodz.org.core_ui.row.unrepeatable_rows.section_title.SectionTitleRowDataModel
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
                makeRow<HorizontalListRow, HorizontalListRowContractor, HorizontalListRowDataModel>(
                    dataModelString,
                    itemClickHandler
                )
        }

        "VerticalListRow" -> {
            clsRow =
                makeRow<VerticalListRow, VerticalListRowContractor, VerticalListRowDataModel>(
                    dataModelString,
                    itemClickHandler
                )
        }

        "CarouselRow" -> {
            clsRow =
                makeRow<CarouselRow, CarouselRowContractor, CarouselRowDataModel>(
                    dataModelString,
                    itemClickHandler
                )
        }

        // ========================================

        // Item Row (Repeatable)
        "EntryItem1Row" -> {
            clsRow =
                makeRow<EntryItem1Row, EntryItem1RowContractor, EntryItem1RowDataModel>(
                    dataModelString,
                    itemClickHandler
                )
        }

        "EntryItem2Row" -> {
            clsRow =
                makeRow<EntryItem2Row, EntryItem2RowContractor, EntryItem2RowDataModel>(
                    dataModelString,
                    itemClickHandler
                )
        }

        "FullWidthImageRow" -> {
            clsRow =
                makeRow<FullWidthImageRow, FullWidthImageRowContractor, FullWidthImageRowDataModel>(
                    dataModelString,
                    itemClickHandler
                )
        }

        "QuoteRow" -> {
            clsRow =
                makeRow<QuoteRow, QuoteRowContractor, QuoteRowDataModel>(
                    dataModelString,
                    itemClickHandler
                )
        }

        "EntryTitle1Row" -> {
            clsRow =
                makeRow<EntryTitle1Row, EntryTitle1RowContractor, EntryTitle1RowDataModel>(
                    dataModelString
                )
        }

        "WebViewRow" -> {
            clsRow =
                makeRow<WebViewRow, WebViewRowContractor, WebViewRowDataModel>(
                    dataModelString
                )
        }

        "LongTextRow" -> {
            clsRow =
                makeRow<LongTextRow, LongTextRowContractor, LongTextRowDataModel>(
                    dataModelString
                )
        }

        "VideoPlayerRow" -> {
            clsRow =
                makeRow<VideoPlayerRow, VideoPlayerRowContractor, VideoPlayerRowDataModel>(
                    dataModelString
                )
        }

        "BoxRow" -> {
            clsRow =
                makeRow<BoxRow, BoxRowContractor, BoxRowDataModel>(
                    dataModelString
                )
        }

        // ========================================

        // Item Row (Unrepeatable)
        "SectionTitleRow" -> {
            clsRow =
                makeRow<SectionTitleRow, SectionTitleRowContractor, SectionTitleRowDataModel>(
                    dataModelString,
                    itemClickHandler
                )
        }

        "SearchBoxRow" -> {
            clsRow =
                makeRow<SearchBoxRow, SearchBoxRowContractor, SearchBoxRowDataModel>(
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