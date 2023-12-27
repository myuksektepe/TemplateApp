package kodz.org.core_ui.row.unrepeatable_item_rows.section_title

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.datamodel.BaseRowDataModel
import kodz.org.core.base.row.row.BaseUnrepeatableItemRow
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass

/**
 * Created by Murat Yüksektepe on 9.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
open class SectionTitleRow(
    override var dataModel: BaseRowDataModel
) : BaseUnrepeatableItemRow() {
    override val layout: Int = R.layout.row_section_title
    override val dataClass: KClass<*> = SectionTitleRowDataModel::class
    override val contractor = SectionTitleRowContractor()
    override var binding: ViewDataBinding? = null
}