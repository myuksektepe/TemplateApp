package kodz.org.core.model.http

import com.google.gson.annotations.SerializedName
import kodz.org.core.model.ErrorType


/**
 * Created by Murat Yüksektepe on 9.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
data class ErrorModel(
    @SerializedName("type") val type: ErrorType?,
    @SerializedName("title") val title: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("primaryButton") val primaryButton: ButtonModel? = null,
    @SerializedName("secondaryButton") val secondaryButton: ButtonModel? = null,
    @SerializedName("isCancelable") val isCancelable: Boolean = true,
)