package kodz.org.feature.screen.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kodz.org.core.R
import kodz.org.core.base.data.http.HttpFlow
import kodz.org.core.base.data.http.HttpRequest
import kodz.org.core.base.data.http.toResponseModel
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.row.BaseRow
import kodz.org.core.base.viewmodel.BaseViewModel
import kodz.org.core.common.AppLog
import kodz.org.core.model.ButtonModel
import kodz.org.core.model.ButtonType
import kodz.org.core.model.DialogBox
import kodz.org.core.model.ErrorModel
import kodz.org.core.model.ErrorType
import kodz.org.core.model.EventTypeCode
import kodz.org.core.model.ItemClickEventModel
import kodz.org.core.model.Resource
import kodz.org.core.model.ScreenModel
import kodz.org.core.model.TabModel
import kodz.org.core_ui.row.common.convertRow
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
    private val httpRequest: HttpRequest
) : BaseViewModel() {

    private var job: Job? = null
    private val componentList = mutableListOf<BaseRow?>()

    private val searchedText = MutableLiveData<String?>()
    val searchedTextLiveData: LiveData<String?> get() = searchedText

    private val itemClickEventModel = MutableLiveData<ItemClickEventModel?>()
    val itemClickEventModelLiveData: LiveData<ItemClickEventModel?> get() = itemClickEventModel

    private val screenModel = MutableLiveData<Resource<ScreenModel.ViewEntity>>()
    val screenModelLiveData: LiveData<Resource<ScreenModel.ViewEntity>> get() = screenModel

    private val itemClickHandler = object : ItemClickHandler {
        override fun onItemClick(itemClickEventModel: ItemClickEventModel?) {
            itemClickEventModel?.let {
                this@ScreenViewModel.itemClickEventModel.postValue(it)
            }
        }
    }

    fun fetchAdapter(endpoint: String?) {
        AppLog("$endpoint isteği başlatıldı.")
        endpoint?.let {
            componentList.clear()
            job?.cancel()
            job = null
            job = viewModelScope.launch(Dispatchers.IO) {
                httpRequest.postRequest<ScreenRequest, ScreenResponse>(context, ScreenRequest(it))
                    .collectLatest { response ->
                        AppLog("$response")
                        when (response) {
                            is HttpFlow.Loading -> {
                                screenModel.postValue(Resource.Loading)
                            }

                            is HttpFlow.Error -> {
                                screenModel.postValue(Resource.Error(response.errorModel))
                            }

                            is HttpFlow.Success -> {
                                (response.data as? ScreenModel)?.let { responseModel ->
                                    // Tabs
                                    if (responseModel.rows?.get(0)?.rowName == "TabsLayout" && responseModel.rows?.get(0)?.isVisible == true) {
                                        val tabsLayout = responseModel.rows?.get(0)?.dataModel?.getAsJsonArray("tabs")
                                        val tabModelList = tabsLayout?.mapNotNull { it.asJsonObject.toResponseModel<TabModel>() }?.toList()

                                        screenModel.postValue(
                                            Resource.Success(
                                                ScreenModel.ViewEntity(
                                                    settings = responseModel.settings,
                                                    error = responseModel.error,
                                                    rows = null,
                                                    tabs = tabModelList
                                                )
                                            )
                                        )
                                    } else {
                                        // Rows
                                        responseModel.rows?.forEach { row ->
                                            if (row.isVisible == true) {
                                                row.rowName?.let { rowName ->
                                                    AppLog("$rowName yaratılmaya başlatıldı.")
                                                    // Make row with rowName
                                                    rowName.convertRow(
                                                        dataModelJsonObject = row.dataModel,
                                                        itemClickHandler = itemClickHandler
                                                    )?.let { row -> componentList.add(row) }
                                                }
                                            }
                                        }

                                        if (componentList.isNotEmpty()) {
                                            screenModel.postValue(
                                                Resource.Success(
                                                    ScreenModel.ViewEntity(
                                                        settings = responseModel.settings,
                                                        error = responseModel.error,
                                                        rows = componentList
                                                    )
                                                )
                                            )
                                            AppLog("Rowlar önyüze gönderildi.")
                                        } else {
                                            screenModel.postValue(
                                                Resource.Error(
                                                    ErrorModel(
                                                        ErrorType.WARNING,
                                                        DialogBox(
                                                            showOnce = false,
                                                            tag = "",
                                                            title = context.getString(R.string.error),
                                                            description = "ScreenViewModel -> componentList is empty!",
                                                            primaryButton = ButtonModel(
                                                                type = ButtonType.FILLED,
                                                                eventType = EventTypeCode.RETRY_LAST_ACTION
                                                            )
                                                        )
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
        }
    }

    fun clearLiveData() {
        itemClickEventModel.postValue(null)
    }

}