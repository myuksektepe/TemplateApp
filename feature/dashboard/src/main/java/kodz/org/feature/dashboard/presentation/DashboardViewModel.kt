package kodz.org.feature.dashboard.presentation

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kodz.org.core.base.component.BaseDataModel
import kodz.org.core.base.component.BaseRow
import kodz.org.core.base.data.http.HttpFlow
import kodz.org.core.base.data.http.HttpRequest
import kodz.org.core.base.data.http.toResponseModel
import kodz.org.core.base.handler.ButtonEventHandler
import kodz.org.core.base.viewmodel.BaseViewModel
import kodz.org.core.component.carousel.CarouselDataModel
import kodz.org.core.component.carousel.CarouselRow
import kodz.org.core.component.carousel_item.CarouselItemDataModel
import kodz.org.core.component.carousel_item.CarouselItemRow
import kodz.org.core.component.section_title.SectionTitleDataModel
import kodz.org.core.component.section_title.SectionTitleRow
import kodz.org.core.model.Resource
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
    private val httpRequest: HttpRequest
) : BaseViewModel() {

    private var job: Job? = null
    private val rowListLiveData = MutableLiveData<Resource<List<BaseRow>>>()
    val rowList: LiveData<Resource<List<BaseRow>>> get() = rowListLiveData

    private val carouselList = mutableListOf<CarouselItemRow>()
    private val componentList = mutableListOf<BaseRow>()


    private val sectionButtonEventHandler = object : ButtonEventHandler {
        override fun onButtonClick() {
            Log.i("applog", "Button click in DashboardViewModel")
        }
    }

    fun fetchAdapter() {
        job?.cancel()
        job = null
        job = viewModelScope.launch(Dispatchers.IO) {

            // Loading
            rowListLiveData.postValue(Resource.Loading)

            /*
            carouselList.add(CarouselItemRow(CarouselItemDataModel(2, "Title 1")))
            val cList = listOf<BaseRow>(
                SectionTitleRow(SectionTitleDataModel("Başlık")),
                CarouselRow(CarouselDataModel(carouselList)),
                SectionTitleRow(SectionTitleDataModel("Başlık")),
            )
            componentList.addAll(cList)
            rowListLiveData.postValue(Resource.Success(componentList))
             */

            // Process
            doSomeMiracle()

            // Error
            /*
            rowListLiveData.postValue(Resource.Error(
                ErrorModel(
                    title = "Üzgünüz :(",
                    description = "Sunucu kaynaklı bir sorun sebebiyle şu anda hizmet veremiyoruz.",
                    isCancelButtonVisible = false,
                    buttonText = "Yeniden Dene",
                    buttonIcon = CommonIcons.REFRESH.resourceId,
                ) {}
            ))
             */


        }
    }

    fun doSomeMiracle() {
        viewModelScope.launch(Dispatchers.IO) {
            httpRequest.postRequest<DashboardRequest, DashboardResponse>(
                context, DashboardRequest()
            ).collectLatest { response ->
                when (response) {
                    is HttpFlow.Success -> {
                        response.data.dashboard?.forEach {
                            val dataModelString = it.dataModel
                            var clsRow: BaseRow? = null

                            it.rowName?.let { rowName ->
                                when (rowName) {
                                    "SectionTitleRow" -> {
                                        val dataModel: BaseDataModel? = dataModelString?.toResponseModel<SectionTitleDataModel>()
                                        clsRow = SectionTitleRow(dataModel!!)
                                    }

                                    "CarouselRow" -> {
                                        val dataModel = dataModelString?.toResponseModel<CarouselDataModel>()
                                        dataModel?.itemList?.forEach { carouselItemData ->
                                            carouselList.add(CarouselItemRow(carouselItemData))
                                        }
                                        clsRow = CarouselRow(dataModel!!)
                                    }

                                    "CarouselItemRow" -> {
                                        val dataModel: BaseDataModel? = dataModelString?.toResponseModel<CarouselItemDataModel>()
                                        clsRow = CarouselItemRow(dataModel!!)
                                    }
                                }

                                clsRow?.let { row -> componentList.add(row) }
                            }

                        }

                        // Send Component List to Adapter
                        rowListLiveData.postValue(Resource.Success(componentList))
                    }

                    else -> {}
                }
            }
        }
    }
}