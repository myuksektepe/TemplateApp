package kodz.org.template.dashboard.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kodz.org.core.base.adapter.model.BaseRow
import kodz.org.core.base.viewmodel.BaseViewModel
import kodz.org.core.base.handler.ButtonEventHandler
import kodz.org.core.component.section_title.SectionTitleDataModel
import kodz.org.template.dashboard.domain.interactor.row.GenerateSectionTitleRow
import javax.inject.Inject


/**
 * Created by Murat Yüksektepe on 9.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */

@HiltViewModel
class DashboardViewModel @Inject constructor() : BaseViewModel() {

    private val rowListLiveData = MutableLiveData<List<BaseRow>>()
    val rowList: LiveData<List<BaseRow>> get() = rowListLiveData

    private val eventHandler = object : ButtonEventHandler {
        override fun onButtonClick() {
            Log.i("applog", "Buton tıklandı ")
        }

        override fun onButtonLongClick() {
            super.onButtonLongClick()
            Log.i("applog", "Buton uzun tıklandı")
        }
    }

    fun fetchAdapter() {
        val rowList = listOf(
            GenerateSectionTitleRow().execute(
                GenerateSectionTitleRow.Params(
                    SectionTitleDataModel("Başlık Metni", "Buton Metni"),
                    eventHandler
                )
            ),
            GenerateSectionTitleRow().execute(
                GenerateSectionTitleRow.Params(
                    SectionTitleDataModel("Başlık Metni 2", "Buton Metni 2"),
                    eventHandler, false
                )
            ),
            GenerateSectionTitleRow().execute(
                GenerateSectionTitleRow.Params(
                    SectionTitleDataModel("Başlık Metni 3", "Buton Metni 3"),
                    eventHandler, false, false
                )
            )
        )
        rowListLiveData.postValue(rowList)
    }
}