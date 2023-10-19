package kodz.org.core.model

import kodz.org.core.model.ErrorModel

sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    data class Error(val errorModel: ErrorModel) : Resource<Nothing>()
    data class Success<out T>(val data: T) : Resource<T>()
}