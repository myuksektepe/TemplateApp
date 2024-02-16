package kodz.org.core_ui.row.unrepeatable_item_rows.tabs_layout

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.datamodel.BaseRowDataModel
import kodz.org.core.base.row.row.BaseUnrepeatableItemRow
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass

class TabsLayoutRow(
    override var dataModel: BaseRowDataModel
) : BaseUnrepeatableItemRow() {
    override val layout: Int = R.layout.row_tabs_layout
    override val dataClass: KClass<*> = TabsLayoutDataModel::class
    override val contractor = TabsLayoutContractor()
    override var binding: ViewDataBinding? = null
}