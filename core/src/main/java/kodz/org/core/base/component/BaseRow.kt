package kodz.org.core.base.component

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.viewmodel.BaseViewModel
import kotlin.reflect.KClass

/**
 * Created by Murat Yüksektepe on 9.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */

/**
 * Ekranda gösterilen bir komponenti hazırlamak için gerekli olan temel sınıf.
 * Bu sınıftan türetilen alt sınıflarda aşağıdaki değişkenler doğru şekilde atanmalıdır.
 */
abstract class BaseRow {
    abstract var dataModel: BaseDataModel
    abstract val layout: Int
    abstract val dataClass: KClass<*>
    abstract val viewModel: BaseViewModel
    abstract val component: BaseComponent
    abstract var binding: ViewDataBinding?
}