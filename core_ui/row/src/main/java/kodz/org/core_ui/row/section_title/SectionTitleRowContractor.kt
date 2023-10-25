package kodz.org.core_ui.row.section_title

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.BaseRowContractor
import kodz.org.core.extension.setSpamProtectedClickListener
import kodz.org.core_ui.row.databinding.RowSectionTitleBinding

/**
 * Created by Murat Yüksektepe on 10.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
class SectionTitleRowContractor : BaseRowContractor() {
    override var binding: ViewDataBinding? = null
    override var itemClickHandler: ItemClickHandler? = null

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        binding = viewDataBinding
        initRow()
    }

    private fun initRow() {
        (binding as? RowSectionTitleBinding)?.run {
            data?.let { data ->

                // EventHandler
                btnSectionViewAll.setSpamProtectedClickListener {
                    itemClickHandler?.onItemClick(data.itemClickEventModel)
                }

            }
        }
    }

}