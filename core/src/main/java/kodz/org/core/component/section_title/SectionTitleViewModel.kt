package kodz.org.core.component.section_title

import android.util.Log
import kodz.org.core.base.viewmodel.BaseViewModel


/**
 * Created by Murat Yüksektepe on 10.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
class SectionTitleViewModel : BaseViewModel() {

    fun onButtonClick() {
        Log.i("applog", "Button click in SectionTitleViewModel")
    }
}