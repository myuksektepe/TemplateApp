package kodz.org.core.component.section_title

import android.content.Context
import androidx.databinding.ViewDataBinding
import kodz.org.core.R
import kodz.org.core.base.adapter.model.BaseDataModel
import kodz.org.core.base.adapter.model.BaseContract
import kotlin.reflect.KClass


/**
 * Created by Murat Yüksektepe on 9.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
class SectionTitleContract(
    private val context: Context,
    override val data: BaseDataModel,
) : BaseContract() {
    override val layout: Int = R.layout.component_section_title
    override val dataClass: KClass<*> = SectionTitleDataModel::class
    override val viewModel = SectionTitleViewModel()
    override val component = SectionTitleComponent(context)
    override val binding: ViewDataBinding? get() = component.binding
}