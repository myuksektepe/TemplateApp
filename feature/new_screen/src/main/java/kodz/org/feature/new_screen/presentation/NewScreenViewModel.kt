package kodz.org.feature.new_screen.presentation

import android.content.Context
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kodz.org.core.base.data.http.HttpRequest
import kodz.org.core.base.viewmodel.BaseViewModel
import kodz.org.core.common.AppLog
import kodz.org.feature.new_screen.data.NewScreenRequest
import kodz.org.feature.new_screen.data.NewScreenResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewScreenViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val httpRequest: HttpRequest
) : BaseViewModel() {

    fun post(endpoint: String) {
        viewModelScope.launch(Dispatchers.IO) {
            httpRequest.postRequest<NewScreenRequest, NewScreenResponse>(context, NewScreenRequest(endpoint)).collectLatest {
                AppLog(it.toString())
            }
        }
    }
}