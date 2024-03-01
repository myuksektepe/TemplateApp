package kodz.org.feature.screen.domain.usecase

import com.google.gson.reflect.TypeToken
import kodz.org.core.base.data.http.gson
import kodz.org.core.common.enums.CommonIcons
import kodz.org.core.common.enums.CommonLottie
import kodz.org.core.data.HttpRequestState
import kodz.org.core.data.repository.RepositoryImpl
import kodz.org.core.model.ButtonModel
import kodz.org.core.model.DialogBox
import kodz.org.core.model.ErrorModel
import kodz.org.core.model.ErrorType
import kodz.org.core.model.EventTypeCode
import kodz.org.core.model.LottieModel
import kodz.org.core.model.ScreenModel
import kodz.org.feature.screen.domain.model.ScreenState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class ScreenUseCase @Inject constructor(
    private val repository: RepositoryImpl
) {

    suspend fun fetchScreen(endpoint: String): Flow<ScreenState<ScreenModel?>> = channelFlow {
        repository.fetchScreenContent(endpoint).collectLatest {
            val result = when (it) {
                is HttpRequestState.Loading -> {
                    ScreenState.Loading
                }

                is HttpRequestState.Error -> {
                    ScreenState.Error(it.message.prepareUnCancelableError())
                }

                is HttpRequestState.Success -> {
                    // Type converting
                    val type = object : TypeToken<ScreenModel>() {}.type
                    val responseObject = gson.fromJson<ScreenModel>(it.content, type)
                    ScreenState.Success(responseObject)
                }
            }
            send(result)
        }
    }.catch {
        emit(
            ScreenState.Error(it.message.prepareUnCancelableError())
        )
    }
}

fun String?.prepareUnCancelableError() = ErrorModel(
    type = ErrorType.BLOCKER,
    dialogBox = DialogBox(
        title = "Beklenmedik Bir Hata Oluştu",
        description = this,
        lottie = LottieModel(CommonLottie.ERROR),
        primaryButton = ButtonModel(
            text = "Geri Dön",
            textColor = null,
            backgroundColor = "#F44336",
            icon = CommonIcons.GO_BACK,
            eventType = EventTypeCode.CLOSE_THE_SCREEN
        ),
        secondaryButton = ButtonModel(
            text = "Yeniden Dene",
            icon = CommonIcons.REFRESH,
            textColor = "#009688",
            backgroundColor = "#ffffff",
            eventType = EventTypeCode.RETRY_LAST_ACTION
        )
    )
)