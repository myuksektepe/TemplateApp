package kodz.org.core.base.row

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
abstract class BaseRow {
    abstract var dataModel: BaseRowDataModel
    abstract val layout: Int
    abstract val dataClass: KClass<*>
    abstract val contractor: BaseRowContractor
    abstract var binding: ViewDataBinding?
}