package kodz.org.template.dashboard.domain.interactor.row

import kodz.org.core.base.adapter.model.BaseRow
import kodz.org.core.base.handler.ButtonEventHandler
import kodz.org.core.component.section_title.SectionTitleRow
import kodz.org.core.component.section_title.SectionTitleDataModel
import javax.inject.Inject


/**
 * Created by Murat Yüksektepe on 10.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
class GenerateSectionTitleRow @Inject constructor() {

    fun execute(
        params: Params
    ): BaseRow = SectionTitleRow(params.dataModel).apply {
        component.eventHandler = params.buttonEventHandler
        viewModel.isButtonEnable.set(params.isButtonEnable == true)
        viewModel.isButtonVisible.set(params.isButtonVisible == true)
    }

    data class Params(
        val dataModel: SectionTitleDataModel,
        val buttonEventHandler: ButtonEventHandler,
        val isButtonEnable: Boolean? = true,
        val isButtonVisible: Boolean? = true,
    )
}