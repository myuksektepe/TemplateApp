package kodz.org.template.dashboard.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kodz.org.core.base.component.BaseRow
import kodz.org.core.base.handler.ButtonEventHandler
import kodz.org.core.base.viewmodel.BaseViewModel
import kodz.org.core.component.carousel.CarouselDataModel
import kodz.org.core.component.carousel.CarouselRow
import kodz.org.core.component.carousel_item.CarouselItemDataModel
import kodz.org.core.component.carousel_item.CarouselItemRow
import kodz.org.core.component.section_title.GenerateSectionTitleRow
import kodz.org.core.component.section_title.SectionTitleDataModel
import kodz.org.core.component.section_title.SectionTitleRow
import kodz.org.core.model.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by Murat Yüksektepe on 9.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */

@HiltViewModel
class DashboardViewModel @Inject constructor() : BaseViewModel() {

    private val rowListLiveData = MutableLiveData<Resource<List<BaseRow>>>()
    val rowList: LiveData<Resource<List<BaseRow>>> get() = rowListLiveData
    private var job: Job? = null

    private val carouselList = listOf(
        CarouselItemRow(
            CarouselItemDataModel(
                14,
                "Yazılımcılar için Heyecan Verici Side Proje Fikirleri",
                "Description 1",
                "https://talentgrid.io/wp-cjontent/uploads/2023/01/pexels-diva-plavalaguna-6147025-1-1-900x604.jpg"
            )
        ),
        CarouselItemRow(
            CarouselItemDataModel(
                27,
                "Hızlı büyüyen bir start-up’ta yazılımcı olmak sana neler katar?",
                "Description 2",
                "https://talentgrid.io/wp-content/uploads/2022/09/dev1-900x604.png"
            )
        ),
        CarouselItemRow(
            CarouselItemDataModel(
                31,
                "Kadın Yazılımcı Platformları \uD83C\uDF89",
                "Description 3",
                "https://talentgrid.io/wp-content/uploads/2022/01/Red-and-Gold-Cute-Illustrative-Greetings-Lunar-New-Year-Video-3-900x604.png"
            )
        ),
    )

    private val sectionButtonEventHandler = object : ButtonEventHandler {
        override fun onButtonClick() {
            Log.i("applog", "Button click in DashboardViewModel")
        }
    }

    fun fetchAdapter() {
        job?.cancel()
        job = null
        job = viewModelScope.launch(Dispatchers.IO) {
            // Loading
            rowListLiveData.postValue(Resource.Loading)

            // Process
            val rowList = listOf(
                // Carousel
                CarouselRow(CarouselDataModel(carouselList)),

                // Section Title
                SectionTitleRow(SectionTitleDataModel("Butonsuz Başlık", isButtonVisible = false)),

                // Section Title
                SectionTitleRow(SectionTitleDataModel("Tüm Konular")),

                // Section Title
                GenerateSectionTitleRow().execute(
                    GenerateSectionTitleRow.Params(
                        SectionTitleDataModel("Tüm Hikayeler"),
                        sectionButtonEventHandler
                    )
                ),

                // Carousel
                CarouselRow(CarouselDataModel(carouselList)),

                // Carousel
                CarouselRow(CarouselDataModel(carouselList)),
            )

            delay(1300)

            /*
            rowListLiveData.postValue(Resource.Error(
                ErrorModel(
                    title = "Üzgünüz :(",
                    description = "Sunucu kaynaklı bir sorun sebebiyle şu anda hizmet veremiyoruz.",
                    isCancelButtonVisible = false,
                    buttonText = "Yeniden Dene",
                    buttonIcon = CommonIcons.REFRESH.resourceId,
                ) {}
            ))
             */

            // Result
            rowListLiveData.postValue(Resource.Success(rowList))
        }
    }
}