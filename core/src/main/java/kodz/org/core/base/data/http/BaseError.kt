package kodz.org.core.base.data.http

open class BaseError(
    private val errorMessage: String?,
    var throwable: Throwable? = null
) : Throwable(errorMessage)