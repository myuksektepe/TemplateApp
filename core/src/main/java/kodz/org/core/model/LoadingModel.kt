package kodz.org.core.model


/**
 * Created by Murat Yüksektepe on 9.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
data class LoadingModel(
    var isShow: Boolean = false,
    val title: String?,
    val description: String? = null
)