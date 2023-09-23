package kodz.org.core.component.section_title

import androidx.databinding.ViewDataBinding
import kodz.org.core.base.component.BaseComponent
import kodz.org.core.base.handler.ButtonEventHandler
import kodz.org.core.databinding.ComponentSectionTitleBinding
import kodz.org.core.extension.setSpamProtectedClickListener
import javax.inject.Inject

/**
 * Created by Murat Yüksektepe on 10.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
class SectionTitleComponent @Inject constructor() : BaseComponent() {
    var eventHandler: ButtonEventHandler? = null
    override var binding: ViewDataBinding? = null

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        binding = viewDataBinding
        initComponent()
    }

    private fun initComponent() {
        (binding as? ComponentSectionTitleBinding)?.run {

            // EventHandler
            btnSectionViewAll.setSpamProtectedClickListener {
                eventHandler?.onButtonClick()
            }

        }
    }

}