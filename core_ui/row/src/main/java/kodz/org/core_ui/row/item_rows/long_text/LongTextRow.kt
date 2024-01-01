package kodz.org.core_ui.row.item_rows.long_text

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.row.BaseItemRow
import kodz.org.core.base.row.datamodel.BaseRowDataModel
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass

/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 6.10.2023.
 */
class LongTextRow(
    override var dataModel: BaseRowDataModel,
    override var isInCarousel: Boolean? = null,
    override var isInList: Boolean?
) : BaseItemRow() {
    override val layout: Int = R.layout.row_long_text
    override val dataClass: KClass<*> = LongTextRowRowDataModel::class
    override val contractor = LongTextRowContractor(isInCarousel, isInList)
    override var binding: ViewDataBinding? = null
}