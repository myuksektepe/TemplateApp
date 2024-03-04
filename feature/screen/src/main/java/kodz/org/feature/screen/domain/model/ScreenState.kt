package kodz.org.feature.screen.domain.model

import kodz.org.core.domain.models.ErrorModel

sealed class ScreenState<out T> {
    object Loading : ScreenState<Nothing>()
    data class Error(val errorModel: ErrorModel.ViewEntity) : ScreenState<Nothing>()
    data class Success<out T>(val data: T) : ScreenState<T>()
}