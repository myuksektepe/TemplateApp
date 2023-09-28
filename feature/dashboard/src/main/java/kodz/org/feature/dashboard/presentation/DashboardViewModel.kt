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
import kodz.org.core.base.viewmodel.BaseViewModel
import kodz.org.core.common.CommonIcons
import kodz.org.core.component.carousel.CarouselContractor
import kodz.org.core.component.carousel.CarouselDataModel
import kodz.org.core.component.carousel.CarouselRow
import kodz.org.core.component.carousel_item.CarouselItemDataModel
import kodz.org.core.component.carousel_item.CarouselItemRow
import kodz.org.core.component.entry_item_1.EntryItem1Contractor
import kodz.org.core.component.entry_item_1.EntryItem1DataModel
import kodz.org.core.component.entry_item_1.EntryItem1Row
import kodz.org.core.component.entry_item_2.EntryItem2Contractor
import kodz.org.core.component.entry_item_2.EntryItem2DataModel
import kodz.org.core.component.entry_item_2.EntryItem2Row
import kodz.org.core.component.makeRow
import kodz.org.core.component.searchbox.SearchBoxContractor
import kodz.org.core.component.searchbox.SearchBoxDataModel
import kodz.org.core.component.searchbox.SearchBoxRow
import kodz.org.core.component.section_title.SectionTitleContractor
import kodz.org.core.component.section_title.SectionTitleDataModel
import kodz.org.core.component.section_title.SectionTitleRow
import kodz.org.core.model.ErrorModel
import kodz.org.core.model.Resource
import kodz.org.feature.dashboard.data.DashboardRequest
import kodz.org.feature.dashboard.data.DashboardResponse
import kodz.org.feature.dashboard.data.SettingsModel
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
    private val httpRequest: HttpRequest
) : BaseViewModel() {

    private var job: Job? = null
    private val componentList = mutableListOf<ComponentBaseRow>()

    private val rowList = MutableLiveData<Resource<List<ComponentBaseRow>>>()
    val rowListLiveData: LiveData<Resource<List<ComponentBaseRow>>> get() = rowList

    private val screenSettings = MutableLiveData<SettingsModel?>()
    val screenSettingsLiveData: LiveData<SettingsModel?> get() = screenSettings


    fun fetchAdapter() {
        job?.cancel()
        job = null
        job = viewModelScope.launch(Dispatchers.IO) {
            httpRequest.postRequest<DashboardRequest, DashboardResponse>(context, DashboardRequest()).collectLatest { response ->
                when (response) {
                    is HttpFlow.Loading -> {
                        rowList.postValue(Resource.Loading)
                    }

                    is HttpFlow.Error -> {
                        rowList.postValue(Resource.Error(
                            ErrorModel(
                                title = "Üzgünüz, bir hata oluştu",
                                description = response.exception.message,
                                isCancelButtonVisible = false,
                                buttonText = "Yeniden Dene",
                                buttonIcon = CommonIcons.REFRESH.resourceId,
                            ) {}
                        ))
                    }

                    is HttpFlow.Success -> {
                        var clsRow: ComponentBaseRow? = null

                        screenSettings.postValue(response.data.settings)

                        // If SearchBox is visible
                        response.data.settings?.isSearchBoxVisible?.let {
                            makeRow<SearchBoxRow, SearchBoxContractor, SearchBoxDataModel>(SearchBoxDataModel())?.let {
                                componentList.add(it)
                            }
                        }

                        // Rows
                        response.data.rows?.forEach {
                            val dataModelString = it.dataModel

                            it.rowName?.let { rowName ->
                                when (rowName) {
                                    "SectionTitleRow" -> {
                                        clsRow = makeRow<SectionTitleRow, SectionTitleContractor, SectionTitleDataModel>(dataModelString)
                                    }

                                    "CarouselRow" -> {
                                        dataModelString?.toResponseModel<CarouselDataModel>()?.let { dataModel ->
                                            dataModel.itemList.forEach { carouselItemData ->
                                                CarouselItemRow(carouselItemData)
                                            }
                                            clsRow = makeRow<CarouselRow, CarouselContractor, CarouselDataModel>(dataModelString)
                                        }
                                    }

                                    "CarouselItemRow" -> {
                                        clsRow = makeRow<CarouselItemRow, CarouselContractor, CarouselItemDataModel>(dataModelString)
                                    }

                                    "SearchBoxRow" -> {
                                        clsRow = makeRow<SearchBoxRow, SearchBoxContractor, SearchBoxDataModel>(dataModelString)
                                    }

                                    "EntryItem1Row" -> {
                                        clsRow = makeRow<EntryItem1Row, EntryItem1Contractor, EntryItem1DataModel>(dataModelString)
                                    }

                                    "EntryItem2Row" -> {
                                        clsRow = makeRow<EntryItem2Row, EntryItem2Contractor, EntryItem2DataModel>(dataModelString)
                                    }
                                }
                                clsRow?.let { row -> componentList.add(row) }
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