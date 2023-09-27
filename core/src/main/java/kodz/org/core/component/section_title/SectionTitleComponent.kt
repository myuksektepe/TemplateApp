package kodz.org.core.component.section_title

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.component.BaseComponent
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.databinding.ComponentSectionTitleBinding
import kodz.org.core.extension.setSpamProtectedClickListener
import javax.inject.Inject

/**
 * Created by Murat Yüksektepe on 10.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
class SectionTitleComponent @Inject constructor() : BaseComponent() {
    override var binding: ViewDataBinding? = null
    override var eventHandler: ItemClickHandler? = null

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        binding = viewDataBinding
        initComponent()
    }

    private fun initComponent() {
        (binding as? ComponentSectionTitleBinding)?.run {

            // EventHandler
            btnSectionViewAll.setSpamProtectedClickListener {
                eventHandler?.onItemClick(data?.clickEventModel)
            }

        }
    }

}