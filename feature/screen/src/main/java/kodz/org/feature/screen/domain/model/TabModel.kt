package kodz.org.feature.screen.domain.model

import com.google.gson.annotations.SerializedName
import kodz.org.core.domain.enums.CommonIcons
import kodz.org.core.domain.models.RowItemModel

data class TabModel(
    @SerializedName("tabTitle") val tabTitle: String?,
    @SerializedName("tabIcon") val tabIcon: CommonIcons?,
    @SerializedName("tabContent") val tabContent: List<RowItemModel?>?
)