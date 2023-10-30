package kodz.org.core_ui.row.categories_slider.categories_slider_item

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.BaseRow
import kodz.org.core.base.row.BaseRowDataModel
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 31.10.2023.
 */
class CategoriesSliderItemRow(override var dataModel: BaseRowDataModel) : BaseRow() {
    override val layout: Int = R.layout.row_categories_slider_item
    override val dataClass: KClass<*> = CategoriesSliderItemDataModel::class
    override val contractor = CategoriesSliderItemContractor()
    override var binding: ViewDataBinding? = null
}