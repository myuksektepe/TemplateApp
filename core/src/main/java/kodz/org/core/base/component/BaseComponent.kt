package kodz.org.core.base.component

import androidx.databinding.ViewDataBinding

/**
 * Created by Murat Yüksektepe on 10.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */

abstract class BaseComponent {
    abstract val binding: ViewDataBinding?
    abstract fun initBinding()
}