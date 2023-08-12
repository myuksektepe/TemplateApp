package kodz.org.core.component.section_title

import androidx.databinding.ObservableBoolean
import kodz.org.core.base.viewmodel.BaseViewModel


/**
 * Created by Murat Yüksektepe on 10.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
class SectionTitleViewModel : BaseViewModel() {
    val isButtonEnable = ObservableBoolean(true)
    val isButtonVisible = ObservableBoolean(true)
    var buttonEventHandler: ButtonEventHandler? = null

    fun onViewAllButtonClick() {
        buttonEventHandler?.onButtonClick()
    }
}