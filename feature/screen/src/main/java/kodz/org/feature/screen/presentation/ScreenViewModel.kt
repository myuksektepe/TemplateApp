package kodz.org.feature.screen.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kodz.org.core.base.data.http.HttpFlow
import kodz.org.core.base.data.http.HttpRequest
import kodz.org.core.base.data.http.toResponseModel
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.BaseRow
import kodz.org.core.base.viewmodel.BaseViewModel
import kodz.org.core.common.AppLog
import kodz.org.core.model.Resource
import kodz.org.core.model.screen.ItemClickEventModel
import kodz.org.core.model.screen.ScreenModel
import kodz.org.core_ui.row.carousel.CarouselRow
import kodz.org.core_ui.row.carousel.CarouselRowContractor
import kodz.org.core_ui.row.carousel.CarouselRowDataModel
import kodz.org.core_ui.row.common.makeRow
import kodz.org.core_ui.row.entry_item_1.EntryItem1Row
import kodz.org.core_ui.row.entry_item_1.EntryItem1RowContractor
import kodz.org.core_ui.row.entry_item_1.EntryItem1RowDataModel
import kodz.org.core_ui.row.entry_item_2.EntryItem2Row
import kodz.org.core_ui.row.entry_item_2.EntryItem2RowContractor
import kodz.org.core_ui.row.entry_item_2.EntryItem2RowDataModel
import kodz.org.core_ui.row.entry_title_1.EntryTitle1Row
import kodz.org.core_ui.row.entry_title_1.EntryTitle1RowContractor
import kodz.org.core_ui.row.entry_title_1.EntryTitle1RowDataModel
import kodz.org.core_ui.row.long_text.LongTextRow
import kodz.org.core_ui.row.long_text.LongTextRowContractor
import kodz.org.core_ui.row.long_text.LongTextRowDataModel
import kodz.org.core_ui.row.searchbox.SearchBoxRow
import kodz.org.core_ui.row.searchbox.SearchBoxRowContractor
import kodz.org.core_ui.row.searchbox.SearchBoxRowDataModel
import kodz.org.core_ui.row.section_title.SectionTitleRow
import kodz.org.core_ui.row.section_title.SectionTitleRowContractor
import kodz.org.core_ui.row.section_title.SectionTitleRowDataModel
import kodz.org.core_ui.row.videoplayer.VideoPlayerRow
import kodz.org.core_ui.row.videoplayer.VideoPlayerRowContractor
import kodz.org.core_ui.row.videoplayer.VideoPlayerRowDataModel
import kodz.org.core_ui.row.webview.WebViewRow
import kodz.org.core_ui.row.webview.WebViewRowContractor
import kodz.org.core_ui.row.webview.WebViewRowDataModel
import kodz.org.feature.screen.data.ScreenRequest
import kodz.org.feature.screen.data.ScreenResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Murat Yüksektepe on 9.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */

@HiltViewModel
class ScreenViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val httpRequest: HttpRequest,
) : BaseViewModel() {

    private var job: Job? = null
    private val componentList = mutableListOf<BaseRow>()

    private val searchedText = MutableLiveData<String?>()
    val searchedTextLiveData: LiveData<String?> get() = searchedText

    private val itemClickEventModel = MutableLiveData<ItemClickEventModel?>()
    val itemClickEventModelLiveData: LiveData<ItemClickEventModel?> get() = itemClickEventModel

    private val screenModel = MutableLiveData<Resource<ScreenModel.ViewEntity>>()
    val screenModelLiveData: LiveData<Resource<ScreenModel.ViewEntity>> get() = screenModel


    fun fetchAdapter(endpoint: String?) {
        endpoint?.let {
            componentList.clear()
            job?.cancel()
            job = null
            job = viewModelScope.launch(Dispatchers.IO) {
                httpRequest.postRequest<ScreenRequest, ScreenResponse>(context, ScreenRequest(it)).collectLatest { response ->
                    when (response) {
                        is HttpFlow.Loading -> {
                            screenModel.postValue(Resource.Loading)
                        }

                        is HttpFlow.Error -> {
                            screenModel.postValue(Resource.Error(response.errorModel))
                        }

                        is HttpFlow.Success -> {
                            var clsRow: BaseRow? = null
                            (response.data as? ScreenModel)?.let {
                                // Rows
                                it.rows?.forEach { row ->
                                    if (row.isVisible == true) {
                                        val dataModelString = row.dataModel
                                        row.rowName?.let { rowName ->
                                            when (rowName) {

                                                // Dashboard
                                                "SectionTitleRow" -> {
                                                    clsRow = makeRow<SectionTitleRow, SectionTitleRowContractor, SectionTitleRowDataModel>(dataModelString, itemClickHandler)
                                                }

                                                "CarouselRow" -> {
                                                    dataModelString?.toResponseModel<CarouselRowDataModel>()?.let { dataModel ->
                                                        dataModel.itemList.forEach { carouselItemData ->
                                                            kodz.org.core_ui.row.carousel_item.CarouselItemRow(carouselItemData)
                                                        }
                                                        clsRow = makeRow<CarouselRow, CarouselRowContractor, CarouselRowDataModel>(dataModelString, itemClickHandler)
                                                    }
                                                }

                                                "EntryItem1Row" -> {
                                                    clsRow = makeRow<EntryItem1Row, EntryItem1RowContractor, EntryItem1RowDataModel>(dataModelString, itemClickHandler)
                                                }

                                                "EntryItem2Row" -> {
                                                    clsRow = makeRow<EntryItem2Row, EntryItem2RowContractor, EntryItem2RowDataModel>(dataModelString, itemClickHandler)
                                                }
                                                // ========================================

                                                // Category
                                                "SearchBoxRow" -> {
                                                    clsRow = makeRow<SearchBoxRow, SearchBoxRowContractor, SearchBoxRowDataModel>(dataModelString)
                                                }
                                                // ========================================

                                                // Entry Details
                                                "EntryTitle1Row" -> {
                                                    clsRow = makeRow<EntryTitle1Row, EntryTitle1RowContractor, EntryTitle1RowDataModel>(dataModelString)
                                                }

                                                "WebViewRow" -> {
                                                    clsRow = makeRow<WebViewRow, WebViewRowContractor, WebViewRowDataModel>(dataModelString)
                                                }

                                                "LongTextRow" -> {
                                                    clsRow = makeRow<LongTextRow, LongTextRowContractor, LongTextRowDataModel>(dataModelString)
                                                }

                                                "VideoPlayerRow" -> {
                                                    clsRow = makeRow<VideoPlayerRow, VideoPlayerRowContractor, VideoPlayerRowDataModel>(dataModelString)
                                                }
                                                // ========================================

                                                else -> {
                                                    clsRow = null
                                                    AppLog("${row.rowName} is not included in app!")
                                                }
                                            }
                                            clsRow?.let { row -> componentList.add(row) }
                                        }
                                    }
                                }

                                screenModel.postValue(
                                    Resource.Success(
                                        ScreenModel.ViewEntity(
                                            it.settings,
                                            it.error,
                                            componentList
                                        )
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private val itemClickHandler = object : ItemClickHandler {
        override fun onItemClick(itemClickEventModel: ItemClickEventModel?) {
            itemClickEventModel?.let {
                this@ScreenViewModel.itemClickEventModel.postValue(it)
            }
        }
    }

    fun clearLiveData() {
        itemClickEventModel.postValue(null)
    }

}