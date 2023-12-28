package kodz.org.core.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kodz.org.core.model.EventTypeCode


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 8.10.2023.
 */
class SharedViewModel : ViewModel() {

    private val eventTypeCodeMutableLiveData = MutableLiveData<EventTypeCode?>()
    val eventTypeCodeLiveData: LiveData<EventTypeCode?> = eventTypeCodeMutableLiveData

    fun setClickEventCode(eventTypeCode: EventTypeCode?) {
        eventTypeCodeMutableLiveData.postValue(eventTypeCode)
    }

    /*
    fun getClickEventCode(): LiveData<EventTypeCode?> {
        return eventTypeCodeLiveData.distinctUntilChanged()
    }

    fun setClickEventCode(eventTypeCode: EventTypeCode?) {
        eventTypeCodeLiveData.postValue(eventTypeCode)
    }
     */
}