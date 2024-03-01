package kodz.org.core.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kodz.org.core.base.VolatileLiveData
import kodz.org.core.domain.enums.EventTypeCode
import javax.inject.Inject


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 8.10.2023.
 */
@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {
    private val eventTypeCodeMutableLiveData = VolatileLiveData<EventTypeCode?>()
    val eventTypeCodeLiveData: LiveData<EventTypeCode?> = eventTypeCodeMutableLiveData

    fun setClickEventCode(eventTypeCode: EventTypeCode?) {
        eventTypeCodeMutableLiveData.postValue(eventTypeCode)
    }
}