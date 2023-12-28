package kodz.org.core.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class SingleMutableLiveData<T> : MutableLiveData<T>() {
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, Observer { value ->
            value?.let {
                observer.onChanged(it)
                postValue(null)
            }
        })
    }
}