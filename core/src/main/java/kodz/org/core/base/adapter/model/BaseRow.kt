package kodz.org.core.base.adapter.model

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.viewmodel.BaseViewModel
import kodz.org.core.base.component.BaseComponent
import kotlin.reflect.KClass

/**
 * Created by Murat Yüksektepe on 9.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
abstract class BaseRow {
    abstract val dataModel: BaseDataModel
    abstract val layout: Int
    abstract val dataClass: KClass<*>
    abstract val viewModel: BaseViewModel
    abstract val component: BaseComponent
    abstract var binding: ViewDataBinding?
}