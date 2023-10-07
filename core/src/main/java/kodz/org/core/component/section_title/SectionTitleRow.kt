package kodz.org.core.component.section_title

import androidx.databinding.ViewDataBinding
import kodz.org.core.R
import kodz.org.core.base.component.ComponentBaseDataModel
import kodz.org.core.base.component.ComponentBaseRow
import kotlin.reflect.KClass


/**
 * Created by Murat Yüksektepe on 9.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
open class SectionTitleRow(
    override var dataModel: ComponentBaseDataModel
) : ComponentBaseRow() {
    override val layout: Int = R.layout.component_section_title
    override val dataClass: KClass<*> = SectionTitleDataModel::class
    override val contractor = SectionTitleContractor()
    override var binding: ViewDataBinding? = null
    // override val viewModel = SectionTitleViewModel()
}