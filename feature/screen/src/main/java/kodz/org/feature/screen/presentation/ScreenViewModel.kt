package kodz.org.feature.screen.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kodz.org.core.R
import kodz.org.core.base.row.row.BaseRow
import kodz.org.core.base.viewmodel.BaseViewModel
import kodz.org.core.common.AppLog
import kodz.org.core.domain.enums.ButtonType
import kodz.org.core.domain.enums.ErrorType
import kodz.org.core.domain.enums.EventTypeCode
import kodz.org.core.domain.extensions.toResponseModel
import kodz.org.core.domain.interfaces.handler.ItemClickHandler
import kodz.org.core.domain.models.ButtonModel
import kodz.org.core.domain.models.ClickEventModel
import kodz.org.core.domain.models.DialogBoxModel
import kodz.org.core.domain.models.ErrorModel
import kodz.org.core.domain.models.TextModel
import kodz.org.core_ui.row.common.convertRow
import kodz.org.feature.screen.domain.model.ScreenModel
import kodz.org.feature.screen.domain.model.ScreenState
import kodz.org.feature.screen.domain.model.TabModel
import kodz.org.feature.screen.domain.usecase.ScreenUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Murat Yüksektepe on 9.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */

@HiltViewModel
class ScreenViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var screenUseCase: ScreenUseCase

    private val searchedText = MutableLiveData<String?>()
    val searchedTextLiveData: LiveData<String?> get() = searchedText

    private val clickEventModel = MutableLiveData<ClickEventModel?>()
    val clickEventModelLiveData: LiveData<ClickEventModel?> get() = clickEventModel

    private val itemClickHandler = object : ItemClickHandler {
        override fun onItemClick(clickEventModel: ClickEventModel?) {
            clickEventModel?.let {
                this@ScreenViewModel.clickEventModel.postValue(it)
            }
        }
    }

    private val componentList = mutableListOf<BaseRow?>()

    private val _screenModel = MutableStateFlow<ScreenState<ScreenModel.ViewEntity?>?>(null)
    val screenModelStateFlow: StateFlow<ScreenState<ScreenModel.ViewEntity?>?> get() = _screenModel

    fun fetchScreen(endpoint: String?) {
        endpoint?.let {
            componentList.clear()
            viewModelScope.launch(Dispatchers.IO) {
                screenUseCase.fetchScreen(it).collectLatest { screenState ->
                    when (screenState) {
                        is ScreenState.Loading -> {
                            _screenModel.value = ScreenState.Loading
                        }

                        is ScreenState.Error -> {
                            _screenModel.value = ScreenState.Error(screenState.errorModel)
                        }

                        is ScreenState.Success -> {
                            screenState.data?.let { responseModel ->
                                // Tabs
                                if (responseModel.rows?.get(0)?.rowName == "TabsLayout" && responseModel.rows?.get(0)?.isVisible == true) {
                                    val tabsLayout = responseModel.rows?.get(0)?.dataModel?.getAsJsonArray("tabs")
                                    val tabModelList = tabsLayout?.mapNotNull { it.asJsonObject.toResponseModel<TabModel>() }?.toList()

                                    _screenModel.value = ScreenState.Success(
                                        ScreenModel.ViewEntity(
                                            settings = responseModel.settings,
                                            error = responseModel.error?.toViewEntity(),
                                            rows = null,
                                            tabs = tabModelList
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
                                        _screenModel.value = ScreenState.Success(
                                            ScreenModel.ViewEntity(
                                                settings = responseModel.settings,
                                                error = responseModel.error?.toViewEntity(),
                                                rows = componentList
                                            )
                                        )
                                        AppLog("Rowlar önyüze gönderildi.")
                                    } else {
                                        _screenModel.value = ScreenState.Error(
                                            ErrorModel.ViewEntity(
                                                ErrorType.WARNING,
                                                DialogBoxModel.ViewEntity(
                                                    showOnce = false,
                                                    tag = "",
                                                    title = TextModel.StaticText(R.string.error),
                                                    description = TextModel.DynamicText("ScreenViewModel -> componentList is empty!"),
                                                    primaryButton = ButtonModel(
                                                        type = ButtonType.FILLED,
                                                        eventType = EventTypeCode.RETRY_LAST_ACTION
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
        clickEventModel.postValue(null)
    }

}