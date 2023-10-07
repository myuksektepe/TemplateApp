package kodz.org.core.component.long_text

import androidx.databinding.ViewDataBinding
import kodz.org.core.R
import kodz.org.core.base.component.ComponentBaseDataModel
import kodz.org.core.base.component.ComponentBaseRow
import kotlin.reflect.KClass


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 6.10.2023.
 */
class LongTextRow(override var dataModel: ComponentBaseDataModel) : ComponentBaseRow() {
    override val layout: Int = R.layout.component_long_text
    override val dataClass: KClass<*> = LongTextDataModel::class
    override val contractor = LongTextContractor()
    override var binding: ViewDataBinding? = null
}