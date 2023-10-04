package kodz.org.core.component.section_title

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.component.ComponentBaseContractor
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.databinding.ComponentSectionTitleBinding
import kodz.org.core.extension.setSpamProtectedClickListener
import javax.inject.Inject

/**
 * Created by Murat Yüksektepe on 10.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
class SectionTitleContractor @Inject constructor() : ComponentBaseContractor() {
    override var binding: ViewDataBinding? = null
    override var itemClickHandler: ItemClickHandler? = null

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        binding = viewDataBinding
        initComponent()
    }

    private fun initComponent() {
        (binding as? ComponentSectionTitleBinding)?.run {
            data?.let { data ->

                // EventHandler
                btnSectionViewAll.setSpamProtectedClickListener {
                    itemClickHandler?.onItemClick(data.clickEventModel)
                }

            }
        }
    }

}