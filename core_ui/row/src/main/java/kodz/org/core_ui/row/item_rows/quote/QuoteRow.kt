package kodz.org.core_ui.row.item_rows.quote

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.BaseRow
import kodz.org.core.base.row.BaseRowDataModel
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 25.10.2023.
 */
class QuoteRow(
    override var dataModel: BaseRowDataModel,
    override var isInSlider: Boolean? = null
) : BaseRow() {
    override val layout: Int = R.layout.row_quote
    override val dataClass: KClass<*> = QuoteRowDataModel::class
    override val contractor = QuoteRowContractor(isInSlider)
    override var binding: ViewDataBinding? = null
}