package kodz.org.core.adapter.model

import kotlin.reflect.KClass


/**
 * Created by Murat Yüksektepe on 9.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
abstract class BaseRowModel {
    abstract val layout: Int
    abstract val dataClass: KClass<*>
    abstract val data: BaseDataModel
}