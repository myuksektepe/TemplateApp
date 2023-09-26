package kodz.org.feature.dashboard.data

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.data.http.HttpBaseResponse
import kodz.org.feature.dashboard.data.model.RowItemModel

data class DashboardResponse(
    @SerializedName("dashboard") val dashboard: ArrayList<RowItemModel>? = null
) : HttpBaseResponse()