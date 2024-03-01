package kodz.org.core.domain.models

import com.google.gson.annotations.SerializedName
import kodz.org.core.domain.enums.ErrorType

data class ErrorModel(
    @SerializedName("type") val type: ErrorType?,
    @SerializedName("dialogBoxModel") val dialogBoxModel: DialogBoxModel?,
)