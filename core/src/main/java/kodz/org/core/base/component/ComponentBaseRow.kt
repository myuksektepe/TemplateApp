package kodz.org.core.base.component

import androidx.databinding.ViewDataBinding
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
abstract class ComponentBaseRow {
    abstract var dataModel: ComponentBaseDataModel
    abstract val layout: Int
    abstract val dataClass: KClass<*>
    abstract val contractor: ComponentBaseContractor
    abstract var binding: ViewDataBinding?
    // abstract val viewModel: ComponentBaseViewModel
}