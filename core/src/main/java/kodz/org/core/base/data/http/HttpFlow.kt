package kodz.org.core.base.data.http

sealed class HttpFlow<out T> {
    data class Success<out T>(val data: T) : HttpFlow<T>()
    data class Error(val exception: BaseError) : HttpFlow<Nothing>()
    object Loading : HttpFlow<Nothing>()
}