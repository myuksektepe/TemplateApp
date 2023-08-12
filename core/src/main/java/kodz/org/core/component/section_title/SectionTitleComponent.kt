package kodz.org.core.component.section_title


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import kodz.org.core.R
import kodz.org.core.base.component.BaseComponent
import kodz.org.core.databinding.ComponentSectionTitleBinding
import kodz.org.core.extension.setSpamProtectedClickListener
import javax.inject.Inject


/**
 * Created by Murat Yüksektepe on 10.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
class SectionTitleComponent @Inject constructor(
    var context: Context,
) : BaseComponent() {
    var eventHandler: ButtonEventHandler? = null
    override var binding: ViewDataBinding? = null

    init {
        initBinding()
    }

    override fun initBinding() {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context), R.layout.component_section_title, null, false
        )
        initComponent()
    }

    private fun initComponent() {
        (binding as ComponentSectionTitleBinding).run {
            btnSectionViewAll.setSpamProtectedClickListener {
                eventHandler?.onButtonClick()
            }

            txtSectionTitle.setSpamProtectedClickListener {
                Log.i("applog", data!!.titleText)
            }
        }
    }

}