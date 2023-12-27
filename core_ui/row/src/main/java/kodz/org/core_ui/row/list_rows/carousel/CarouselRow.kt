package kodz.org.core_ui.row.list_rows.carousel

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.row.BaseListRow
import kodz.org.core.base.row.datamodel.BaseRowDataModel
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 1.11.2023.
 */
class CarouselRow(
    override var dataModel: BaseRowDataModel
) : BaseListRow() {
    override val layout: Int = R.layout.row_carousel
    override val dataClass: KClass<*> = CarouselRowDataModel::class
    override val contractor = CarouselRowContractor()
    override var binding: ViewDataBinding? = null
}