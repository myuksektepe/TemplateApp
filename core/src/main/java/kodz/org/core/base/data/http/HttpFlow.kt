package kodz.org.core.base.data.http

import kodz.org.core.model.ErrorModel

sealed class HttpFlow<out T> {
    data class Success<out T>(val data: T) : HttpFlow<T>()
    data class Error(val errorModel: ErrorModel) : HttpFlow<Nothing>()
    object Loading : HttpFlow<Nothing>()
}