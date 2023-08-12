package kodz.org.core.component.section_title

import kodz.org.core.base.adapter.model.BaseDataModel


/**
 * Created by Murat Yüksektepe on 9.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
data class SectionTitleDataModel(
    val titleText: String,
    val buttonText: String? = null,
) : BaseDataModel