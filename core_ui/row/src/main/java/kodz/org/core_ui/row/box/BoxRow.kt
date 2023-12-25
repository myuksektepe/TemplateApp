package kodz.org.core_ui.row.box

import android.content.Context
import androidx.databinding.ViewDataBinding
import dagger.hilt.android.qualifiers.ApplicationContext
import kodz.org.core.base.row.BaseRow
import kodz.org.core.base.row.BaseRowDataModel
import kodz.org.core_ui.row.R
import kotlin.reflect.KClass

class BoxRow(
    override var dataModel: BaseRowDataModel,
    override var isInSlider: Boolean?,
) : BaseRow() {
    override val layout: Int = R.layout.row_box
    override val dataClass: KClass<*> = BoxRowDataModel::class
    override val contractor = BoxRowContractor()
    override var binding: ViewDataBinding? = null
}