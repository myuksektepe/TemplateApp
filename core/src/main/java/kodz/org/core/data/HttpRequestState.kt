package kodz.org.core.data

sealed class HttpRequestState<out T> {
    object Loading : HttpRequestState<Nothing>()
    data class Error(val message: String) : HttpRequestState<Nothing>()
    data class Success<out T>(val content: T) : HttpRequestState<T>()
}