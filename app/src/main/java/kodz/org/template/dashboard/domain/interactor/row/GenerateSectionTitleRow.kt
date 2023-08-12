package kodz.org.template.dashboard.domain.interactor.row

import android.content.Context
import kodz.org.core.base.adapter.model.BaseContract
import kodz.org.core.component.section_title.ButtonEventHandler
import kodz.org.core.component.section_title.SectionTitleContract
import kodz.org.core.component.section_title.SectionTitleDataModel
import javax.inject.Inject


/**
 * Created by Murat Yüksektepe on 10.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
class GenerateSectionTitleRow @Inject constructor(
    private val context: Context,
) {

    fun execute(
        params: Params
    ): BaseContract = SectionTitleContract(context, params.sectionTitleData).apply {
        // component.eventHandler = params.buttonEventHandler
        viewModel.isButtonEnable.set(params.isButtonEnable == true)
        viewModel.isButtonVisible.set(params.isButtonVisible == true)
        viewModel.buttonEventHandler = params.buttonEventHandler
    }

    data class Params(
        val sectionTitleData: SectionTitleDataModel,
        val buttonEventHandler: ButtonEventHandler,
        val isButtonEnable: Boolean? = true,
        val isButtonVisible: Boolean? = true,
    )
}