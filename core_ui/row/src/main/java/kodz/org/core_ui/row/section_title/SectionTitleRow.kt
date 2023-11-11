package kodz.org.core_ui.row.section_title

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.BaseRow
import kodz.org.core.base.row.BaseRowDataModel
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass

/**
 * Created by Murat Yüksektepe on 9.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
open class SectionTitleRow(
    override var dataModel: BaseRowDataModel,
    override var isInSlider: Boolean? = null
) : BaseRow() {
    override val layout: Int = R.layout.row_section_title
    override val dataClass: KClass<*> = SectionTitleRowDataModel::class
    override val contractor = SectionTitleRowContractor()
    override var binding: ViewDataBinding? = null
}