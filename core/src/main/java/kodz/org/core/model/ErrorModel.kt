package kodz.org.core.model


/**
 * Created by Murat Yüksektepe on 9.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
data class ErrorModel(
    val title: String? = null,
    val description: String? = null,
    val buttonText: String? = null,
    val buttonIcon: Int? = null,
    val isCancelButtonVisible: Boolean = true,
    val buttonCallback: (() -> Unit?)? = null,
)