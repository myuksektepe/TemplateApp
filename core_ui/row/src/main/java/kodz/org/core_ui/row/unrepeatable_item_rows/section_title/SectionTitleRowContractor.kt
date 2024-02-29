package kodz.org.core_ui.row.unrepeatable_item_rows.section_title

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.contractor.BaseUnrepeatableItemRowContractor
import kodz.org.core.extension.setSpamProtectedClickListener
import kodz.org.core_ui.row.databinding.RowSectionTitleBinding

/**
 * Created by Murat Yüksektepe on 10.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
class SectionTitleRowContractor : BaseUnrepeatableItemRowContractor() {
    override var viewBinding: ViewDataBinding? = null
    lateinit var binding: RowSectionTitleBinding
    override var itemClickHandler: ItemClickHandler? = null

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        viewBinding = viewDataBinding
        binding = viewDataBinding as RowSectionTitleBinding
        initRow()
    }

    private fun initRow() {
        binding.run {
            data?.let { data ->

                // EventHandler
                btnSectionViewAll.setSpamProtectedClickListener {
                    itemClickHandler?.onItemClick(data.clickEventModel)
                }

            }
        }
    }

}