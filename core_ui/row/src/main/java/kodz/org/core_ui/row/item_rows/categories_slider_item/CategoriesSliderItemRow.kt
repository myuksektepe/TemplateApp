package kodz.org.core_ui.row.item_rows.categories_slider_item

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.row.BaseItemRow
import kodz.org.core.base.row.datamodel.BaseRowDataModel
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 31.10.2023.
 */
class CategoriesSliderItemRow(
    override var dataModel: BaseRowDataModel,
    override var isInSlider: Boolean? = null,
    override var isInList: Boolean?
) : BaseItemRow() {
    override val layout: Int = R.layout.row_categories_slider_item
    override val dataClass: KClass<*> = CategoriesSliderItemRowRowDataModel::class
    override val contractor = CategoriesSliderItemRowContractor(isInSlider, isInList)
    override var binding: ViewDataBinding? = null
}