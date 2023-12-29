package kodz.org.core.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kodz.org.core.base.VolatileLiveData
import kodz.org.core.model.EventTypeCode


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 8.10.2023.
 */
class SharedViewModel : ViewModel() {
    private val eventTypeCodeMutableLiveData = VolatileLiveData<EventTypeCode?>()
    val eventTypeCodeLiveData: LiveData<EventTypeCode?> = eventTypeCodeMutableLiveData

    fun setClickEventCode(eventTypeCode: EventTypeCode?) {
        eventTypeCodeMutableLiveData.postValue(eventTypeCode)
    }
}