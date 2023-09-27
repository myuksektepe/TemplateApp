package kodz.org.core.component.section_title

import androidx.databinding.ViewDataBinding
import kodz.org.core.R
import kodz.org.core.base.component.BaseDataModel
import kodz.org.core.base.component.BaseRow
import kotlin.reflect.KClass


/**
 * Created by Murat Yüksektepe on 9.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
open class SectionTitleRow(
    override var dataModel: BaseDataModel
) : BaseRow() {
    override val layout: Int = R.layout.component_section_title
    override val dataClass: KClass<*> = SectionTitleDataModel::class
    override val viewModel = SectionTitleViewModel()
    override val component = SectionTitleComponent()
    override var binding: ViewDataBinding? = null
}