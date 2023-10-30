package kodz.org.core_ui.row.categories_slider

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.BaseRow
import kodz.org.core.base.row.BaseRowDataModel
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 31.10.2023.
 */
class CategoriesSliderRow(override var dataModel: BaseRowDataModel) : BaseRow() {

    override val layout: Int = R.layout.row_categories_slider
    override val dataClass: KClass<*> = CategoriesSliderDataModel::class
    override val contractor = CategoriesSliderContractor()
    override var binding: ViewDataBinding? = null
}