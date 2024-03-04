package kodz.org.core.base.row.contractor

import androidx.databinding.ViewDataBinding
import kodz.org.core.domain.interfaces.handler.ItemClickHandler

/**
 * Created by Murat Yüksektepe on 10.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */

/**
 * Bir komponent yaratmak için kullanılan temel sınıf.
 * Bu sınıftan türetilen sınıflarda komponentin görüntüsü üzerinde
 * binding kullanıralak atamalar yapılır.
 */
abstract class BaseRowContractor {
    abstract var itemClickHandler: ItemClickHandler?
    abstract var viewBinding: ViewDataBinding?
    abstract fun initBinding(viewDataBinding: ViewDataBinding)
}