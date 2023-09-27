package kodz.org.feature.dashboard.presentation

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kodz.org.core.base.component.BaseComponent
import kodz.org.core.base.component.BaseDataModel
import kodz.org.core.base.component.BaseRow
import kodz.org.core.base.data.http.HttpFlow
import kodz.org.core.base.data.http.HttpRequest
import kodz.org.core.base.data.http.toResponseModel
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.viewmodel.BaseViewModel
import kodz.org.core.common.CommonIcons
import kodz.org.core.component.carousel.CarouselComponent
import kodz.org.core.component.carousel.CarouselDataModel
import kodz.org.core.component.carousel.CarouselRow
import kodz.org.core.component.carousel_item.CarouselItemDataModel
import kodz.org.core.component.carousel_item.CarouselItemRow
import kodz.org.core.component.section_title.SectionTitleComponent
import kodz.org.core.component.section_title.SectionTitleDataModel
import kodz.org.core.component.section_title.SectionTitleRow
import kodz.org.core.model.ClickEventModel
import kodz.org.core.model.ErrorModel
import kodz.org.core.model.Resource
import kodz.org.feature.dashboard.data.DashboardRequest
import kodz.org.feature.dashboard.data.DashboardResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.full.primaryConstructor


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

    private val componentList = mutableListOf<BaseRow>()

    fun fetchAdapter() {
        job?.cancel()
        job = null
        job = viewModelScope.launch(Dispatchers.IO) {
            httpRequest.postRequest<DashboardRequest, DashboardResponse>(context, DashboardRequest()).collectLatest { response ->
                when (response) {
                    is HttpFlow.Loading -> {
                        rowListLiveData.postValue(Resource.Loading)
                    }

                    is HttpFlow.Error -> {
                        rowListLiveData.postValue(Resource.Error(
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
                        response.data.dashboard?.forEach {
                            val dataModelString = it.dataModel
                            var clsRow: BaseRow? = null

                            it.rowName?.let { rowName ->
                                when (rowName) {
                                    "SectionTitleRow" -> {
                                        clsRow = makeRow<SectionTitleDataModel, SectionTitleRow, SectionTitleComponent>(dataModelString)
                                        /*
                                        dataModelString?.toResponseModel<SectionTitleDataModel>()?.let { dataModel ->
                                            clsRow = SectionTitleRow(dataModel).apply {
                                                component.eventHandler = object : ItemClickHandler {
                                                    override fun onItemClick(clickEventModel: ClickEventModel?) {
                                                        Log.i("applog", clickEventModel.toString())
                                                    }
                                                }
                                            }
                                        }
                                         */
                                    }

                                    "CarouselRow" -> {
                                        dataModelString?.toResponseModel<CarouselDataModel>()?.let { dataModel ->
                                            dataModel.itemList.forEach { carouselItemData ->
                                                CarouselItemRow(carouselItemData)
                                            }
                                            clsRow = makeRow<CarouselDataModel, CarouselRow, CarouselComponent>(dataModelString)
                                        }
                                    }

                                    "CarouselItemRow" -> {
                                        clsRow = makeRow<CarouselItemDataModel, CarouselItemRow, CarouselComponent>(dataModelString)
                                    }
                                }

                                clsRow?.let { row -> componentList.add(row) }
                            }

                        }

                        // Send Component List to Adapter
                        rowListLiveData.postValue(Resource.Success(componentList))
                    }
                }
            }
        }
    }

    private inline fun <reified BDM : BaseDataModel, reified R : BaseRow, reified C : BaseComponent> makeRow(dataModelString: String?): BaseRow? {
        var row: BaseRow? = null
        dataModelString?.toResponseModel<BDM>()?.let { dataModel ->
            row = R::class.primaryConstructor?.call(dataModel)?.apply {
                (component as? C)?.eventHandler = object : ItemClickHandler {
                    override fun onItemClick(clickEventModel: ClickEventModel?) {
                        Log.i("applog", clickEventModel.toString())
                    }
                }
            }
        }
        return row
    }
}