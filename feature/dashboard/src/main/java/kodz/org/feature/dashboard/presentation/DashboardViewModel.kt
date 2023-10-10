package kodz.org.feature.dashboard.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kodz.org.core.base.component.ComponentBaseRow
import kodz.org.core.base.data.http.HttpFlow
import kodz.org.core.base.data.http.HttpRequest
import kodz.org.core.base.data.http.toResponseModel
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.viewmodel.BaseViewModel
import kodz.org.core.common.AppLog
import kodz.org.core.component.carousel.CarouselContractor
import kodz.org.core.component.carousel.CarouselDataModel
import kodz.org.core.component.carousel.CarouselRow
import kodz.org.core.component.carousel_item.CarouselItemRow
import kodz.org.core.component.entry_item_1.EntryItem1Contractor
import kodz.org.core.component.entry_item_1.EntryItem1DataModel
import kodz.org.core.component.entry_item_1.EntryItem1Row
import kodz.org.core.component.entry_item_2.EntryItem2Contractor
import kodz.org.core.component.entry_item_2.EntryItem2DataModel
import kodz.org.core.component.entry_item_2.EntryItem2Row
import kodz.org.core.component.entry_title_1.EntryTitle1Contractor
import kodz.org.core.component.entry_title_1.EntryTitle1DataModel
import kodz.org.core.component.entry_title_1.EntryTitle1Row
import kodz.org.core.component.long_text.LongTextContractor
import kodz.org.core.component.long_text.LongTextDataModel
import kodz.org.core.component.long_text.LongTextRow
import kodz.org.core.component.makeRow
import kodz.org.core.component.searchbox.SearchBoxContractor
import kodz.org.core.component.searchbox.SearchBoxDataModel
import kodz.org.core.component.searchbox.SearchBoxRow
import kodz.org.core.component.section_title.SectionTitleContractor
import kodz.org.core.component.section_title.SectionTitleDataModel
import kodz.org.core.component.section_title.SectionTitleRow
import kodz.org.core.component.videoplayer.VideoPlayerContractor
import kodz.org.core.component.videoplayer.VideoPlayerDataModel
import kodz.org.core.component.videoplayer.VideoPlayerRow
import kodz.org.core.component.webview.WebViewContractor
import kodz.org.core.component.webview.WebViewDataModel
import kodz.org.core.component.webview.WebViewRow
import kodz.org.core.model.Resource
import kodz.org.core.model.screen.ItemClickEventModel
import kodz.org.core.model.screen.SettingsModel
import kodz.org.feature.dashboard.data.DashboardRequest
import kodz.org.feature.dashboard.data.DashboardResponse
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
class DashboardViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val httpRequest: HttpRequest,
) : BaseViewModel() {

    private var job: Job? = null
    private val componentList = mutableListOf<ComponentBaseRow>()

    private val rowList = MutableLiveData<Resource<List<ComponentBaseRow>>>()
    val rowListLiveData: LiveData<Resource<List<ComponentBaseRow>>> get() = rowList

    private val screenSettings = MutableLiveData<SettingsModel?>()
    val screenSettingsLiveData: LiveData<SettingsModel?> get() = screenSettings

    private val searchedText = MutableLiveData<String?>()
    val searchedTextLiveData: LiveData<String?> get() = searchedText

    private val itemClickEventModel = MutableLiveData<ItemClickEventModel?>()
    val itemClickEventModelLiveData: LiveData<ItemClickEventModel?> get() = itemClickEventModel

    fun fetchAdapter(endpoint: String?) {
        endpoint?.let {
            componentList.clear()
            job?.cancel()
            job = null
            job = viewModelScope.launch(Dispatchers.IO) {
                httpRequest.postRequest<DashboardRequest, DashboardResponse>(context, DashboardRequest(it)).collectLatest { response ->
                    when (response) {
                        is HttpFlow.Loading -> {
                            rowList.postValue(Resource.Loading)
                        }

                        is HttpFlow.Error -> {
                            rowList.postValue(Resource.Error(response.errorModel))
                        }

                        is HttpFlow.Success -> {
                            var clsRow: ComponentBaseRow? = null

                            // Screen settings
                            screenSettings.postValue(response.data.settings)

                            // Rows
                            response.data.rows?.forEach {
                                if (it.isVisible == true) {
                                    val dataModelString = it.dataModel
                                    it.rowName?.let { rowName ->
                                        when (rowName) {

                                            // Dashboard
                                            "SectionTitleRow" -> {
                                                clsRow = makeRow<SectionTitleRow, SectionTitleContractor, SectionTitleDataModel>(dataModelString, itemClickHandler)
                                            }

                                            "CarouselRow" -> {
                                                dataModelString?.toResponseModel<CarouselDataModel>()?.let { dataModel ->
                                                    dataModel.itemList.forEach { carouselItemData ->
                                                        CarouselItemRow(carouselItemData)
                                                    }
                                                    clsRow = makeRow<CarouselRow, CarouselContractor, CarouselDataModel>(dataModelString, itemClickHandler)
                                                }
                                            }

                                            "EntryItem1Row" -> {
                                                clsRow = makeRow<EntryItem1Row, EntryItem1Contractor, EntryItem1DataModel>(dataModelString, itemClickHandler)
                                            }

                                            "EntryItem2Row" -> {
                                                clsRow = makeRow<EntryItem2Row, EntryItem2Contractor, EntryItem2DataModel>(dataModelString, itemClickHandler)
                                            }
                                            // ========================================

                                            // Category
                                            "SearchBoxRow" -> {
                                                clsRow = makeRow<SearchBoxRow, SearchBoxContractor, SearchBoxDataModel>(dataModelString)
                                            }
                                            // ========================================

                                            // Entry Details
                                            "EntryTitle1Row" -> {
                                                clsRow = makeRow<EntryTitle1Row, EntryTitle1Contractor, EntryTitle1DataModel>(dataModelString)
                                            }

                                            "WebViewRow" -> {
                                                clsRow = makeRow<WebViewRow, WebViewContractor, WebViewDataModel>(dataModelString)
                                            }

                                            "LongTextRow" -> {
                                                clsRow = makeRow<LongTextRow, LongTextContractor, LongTextDataModel>(dataModelString)
                                            }

                                            "VideoPlayerRow" -> {
                                                clsRow = makeRow<VideoPlayerRow, VideoPlayerContractor, VideoPlayerDataModel>(dataModelString)
                                            }
                                            // ========================================

                                            else -> {
                                                clsRow = null
                                                AppLog("${it.rowName} is not included in app!")
                                            }
                                        }
                                        clsRow?.let { row -> componentList.add(row) }
                                    }
                                }
                            }

                            // Send Component List to Adapter
                            rowList.postValue(Resource.Success(componentList))
                        }
                    }
                }
            }
        }
    }

    private val itemClickHandler = object : ItemClickHandler {
        override fun onItemClick(itemClickEventModel: ItemClickEventModel?) {
            itemClickEventModel?.let {
                this@DashboardViewModel.itemClickEventModel.postValue(it)
            }
        }
    }

    fun clearLiveData() {
        itemClickEventModel.postValue(null)
    }

}