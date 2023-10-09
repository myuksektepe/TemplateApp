package kodz.org.core.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kodz.org.core.model.screen.EventTypeCode
import javax.inject.Inject


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 8.10.2023.
 */
@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {

    private val eventTypeCodeLiveData = MutableLiveData<EventTypeCode?>()

    fun getClickEventCode(): LiveData<EventTypeCode?> {
        return eventTypeCodeLiveData
    }

    fun setClickEventCode(eventTypeCode: EventTypeCode?) {
        eventTypeCodeLiveData.postValue(eventTypeCode)
    }
}