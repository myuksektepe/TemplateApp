package kodz.org.core.model


/**
 * Created by Murat Yüksektepe on 9.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
enum class ErrorType(val type: String) {
    BLOCKER("blocker"),
    WARNING("warning");

    companion object {
        infix fun from(type: String): ErrorType? = ErrorType.values().firstOrNull { it.type == type }
    }
}