package kodz.org.core.component.carousel_item

import android.util.Log
import kodz.org.core.base.viewmodel.BaseViewModel

class CarouselItemViewModel : BaseViewModel() {

    fun onItemClick(id: Int) {
        Log.i("applog", "$id. Id'li carousel item tıklandı.")
    }
}