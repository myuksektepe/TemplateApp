package kodz.org.core_ui.row.item_rows.quote

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.row.BaseItemRow
import kodz.org.core.base.row.datamodel.BaseRowDataModel
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 25.10.2023.
 */
class QuoteRow(
    override var dataModel: BaseRowDataModel,
    override var isInCarousel: Boolean? = null,
    override var isInList: Boolean?
) : BaseItemRow() {
    override val layout: Int = R.layout.row_quote
    override val dataClass: KClass<*> = QuoteRowRowDataModel::class
    override val contractor = QuoteRowContractor(isInCarousel, isInList)
    override var binding: ViewDataBinding? = null
}