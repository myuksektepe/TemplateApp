package kodz.org.core.domain.models

import com.google.gson.annotations.SerializedName
import kodz.org.core.domain.enums.ErrorType

class ErrorModel(
    @SerializedName("type") val type: ErrorType?,
    @SerializedName("dialogBoxModel") val dialogBoxModel: DialogBoxModel?,
) {
    class ViewEntity(
        val type: ErrorType?,
        val dialogBoxModel: DialogBoxModel.ViewEntity?
    )

    fun toViewEntity() = ViewEntity(
        type = this.type,
        dialogBoxModel = this.dialogBoxModel?.toViewEntity()
    )
}