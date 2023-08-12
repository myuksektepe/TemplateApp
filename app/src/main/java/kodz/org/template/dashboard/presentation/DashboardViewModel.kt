package kodz.org.template.dashboard.presentation

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kodz.org.core.base.adapter.model.BaseContract
import kodz.org.core.base.viewmodel.BaseViewModel
import kodz.org.core.component.section_title.ButtonEventHandler
import kodz.org.core.component.section_title.SectionTitleDataModel
import kodz.org.template.dashboard.domain.interactor.row.GenerateSectionTitleRow
import javax.inject.Inject


/**
 * Created by Murat Yüksektepe on 9.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */

@HiltViewModel
class DashboardViewModel @Inject constructor(
    @ApplicationContext val context: Context
) : BaseViewModel() {

    private val rowListLiveData = MutableLiveData<List<BaseContract>>()
    val rowList: LiveData<List<BaseContract>> get() = rowListLiveData

    private val eventHandler = object : ButtonEventHandler {
        override fun onButtonClick() {
            Log.i("applog", "İlk buton tıklandı ")
        }
    }

    fun fetchAdapter() {
        val rowList = listOf(
            GenerateSectionTitleRow(context).execute(
                GenerateSectionTitleRow.Params(
                    SectionTitleDataModel("Başlık Metni", "Buton Metni"),
                    eventHandler
                )
            ),
            GenerateSectionTitleRow(context).execute(
                GenerateSectionTitleRow.Params(
                    SectionTitleDataModel("Başlık Metni 2", "Buton Metni 2"),
                    eventHandler, false
                )
            ),
            GenerateSectionTitleRow(context).execute(
                GenerateSectionTitleRow.Params(
                    SectionTitleDataModel("Başlık Metni 3", "Buton Metni 3"),
                    eventHandler, false, false
                )
            )
        )
        rowListLiveData.postValue(rowList)
    }
}