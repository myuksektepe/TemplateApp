package kodz.org.core.component.section_title

import com.google.gson.annotations.SerializedName
import kodz.org.core.base.component.BaseDataModel

/**
 * Created by Murat Yüksektepe on 9.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */

data class SectionTitleDataModel(
    @SerializedName("titleText") val titleText: String,
    @SerializedName("isButtonVisible") val isButtonVisible: Boolean = true,
    @SerializedName("isButtonEnable") val isButtonEnable: Boolean = true,
    @SerializedName("buttonText") val buttonText: String? = null,
) : BaseDataModel