package kodz.org.core_ui.row.long_text

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.BaseRow
import kodz.org.core.base.row.BaseRowDataModel
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass

/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 6.10.2023.
 */
class LongTextRow(override var dataModel: BaseRowDataModel) : BaseRow() {
    override val layout: Int = R.layout.row_long_text
    override val dataClass: KClass<*> = LongTextRowDataModel::class
    override val contractor = LongTextRowContractor()
    override var binding: ViewDataBinding? = null
}