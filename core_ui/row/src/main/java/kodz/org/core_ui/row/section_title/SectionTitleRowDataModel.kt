package kodz.org.core_ui.row.section_title

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.row.BaseRowDataModel
import kodz.org.core.model.screen.ItemClickEventModel

/**
 * Created by Murat Yüksektepe on 9.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */

data class SectionTitleRowDataModel(
    @SerializedName("titleText") val titleText: String? = null,
    @SerializedName("isButtonVisible") val isButtonVisible: Boolean = true,
    @SerializedName("isButtonEnable") val isButtonEnable: Boolean = true,
    @SerializedName("buttonText") val buttonText: String? = null,
    @SerializedName("clickEventModel") override val itemClickEventModel: ItemClickEventModel? = null
) : BaseRowDataModel()