package kodz.org.core.base.component

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.handler.ItemClickHandler

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
abstract class BaseComponent {
    abstract var eventHandler: ItemClickHandler?
    abstract var binding: ViewDataBinding?
    abstract fun initBinding(viewDataBinding: ViewDataBinding)
}