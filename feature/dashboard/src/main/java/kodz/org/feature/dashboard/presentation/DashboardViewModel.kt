package kodz.org.feature.dashboard.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kodz.org.core.base.component.BaseRow
import kodz.org.core.base.data.http.HttpFlow
import kodz.org.core.base.data.http.HttpRequest
import kodz.org.core.base.data.http.toResponseModel
import kodz.org.core.base.viewmodel.BaseViewModel
import kodz.org.core.common.CommonIcons
import kodz.org.core.component.carousel.CarouselComponent
import kodz.org.core.component.carousel.CarouselDataModel
import kodz.org.core.component.carousel.CarouselRow
import kodz.org.core.component.carousel_item.CarouselItemDataModel
import kodz.org.core.component.carousel_item.CarouselItemRow
import kodz.org.core.component.makeRow
import kodz.org.core.component.searchbox.SearchBoxComponent
import kodz.org.core.component.searchbox.SearchBoxDataModel
import kodz.org.core.component.searchbox.SearchBoxRow
import kodz.org.core.component.section_title.SectionTitleComponent
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
    private val componentList = mutableListOf<BaseRow>()

    private val rowList = MutableLiveData<Resource<List<BaseRow>>>()
    val rowListLiveData: LiveData<Resource<List<BaseRow>>> get() = rowList

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
                        var clsRow: BaseRow? = null

                        screenSettings.postValue(response.data.settings)

                        // If SearchBox is visible
                        response.data.settings?.isSearchBoxVisible?.let {
                            makeRow<SearchBoxRow, SearchBoxComponent, SearchBoxDataModel>(SearchBoxDataModel())?.let {
                                componentList.add(it)
                            }
                        }

                        // Rows
                        response.data.rows?.forEach {
                            val dataModelString = it.dataModel

                            it.rowName?.let { rowName ->
                                when (rowName) {
                                    "SectionTitleRow" -> {
                                        clsRow = makeRow<SectionTitleRow, SectionTitleComponent, SectionTitleDataModel>(dataModelString)
                                    }

                                    "CarouselRow" -> {
                                        dataModelString?.toResponseModel<CarouselDataModel>()?.let { dataModel ->
                                            dataModel.itemList.forEach { carouselItemData ->
                                                CarouselItemRow(carouselItemData)
                                            }
                                            clsRow = makeRow<CarouselRow, CarouselComponent, CarouselDataModel>(dataModelString)
                                        }
                                    }

                                    "CarouselItemRow" -> {
                                        clsRow = makeRow<CarouselItemRow, CarouselComponent, CarouselItemDataModel>(dataModelString)
                                    }

                                    "SearchBoxRow" -> {
                                        clsRow = makeRow<SearchBoxRow, SearchBoxComponent, SearchBoxDataModel>(dataModelString)
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