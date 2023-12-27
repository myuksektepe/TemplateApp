package kodz.org.core_ui.row.list_rows.horizontal_list

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.row.BaseListRow
import kodz.org.core.base.row.datamodel.BaseRowDataModel
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 31.10.2023.
 */
class HorizontalListRow(
    override var dataModel: BaseRowDataModel
) : BaseListRow() {
    override val layout: Int = R.layout.row_horizontal_list
    override val dataClass: KClass<*> = HorizontalListRowDataModel::class
    override val contractor = HorizontalListRowContractor()
    override var binding: ViewDataBinding? = null
}