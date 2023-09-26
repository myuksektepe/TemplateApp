package kodz.org.feature.dashboard.presentation.row

import kodz.org.core.base.component.BaseRow
import kodz.org.core.base.component.GenerateBaseRow
import kodz.org.core.base.handler.ButtonEventHandler
import kodz.org.core.component.section_title.SectionTitleDataModel
import kodz.org.core.component.section_title.SectionTitleRow


/**
 * Created by Murat Yüksektepe on 10.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
class GenerateSectionTitleRow : GenerateBaseRow<GenerateSectionTitleRow.Params>() {

    override fun execute(
        params: Params
    ): BaseRow = SectionTitleRow(params.dataModel).apply {
        component.eventHandler = params.buttonEventHandler
    }

    data class Params(
        val dataModel: SectionTitleDataModel,
        val buttonEventHandler: ButtonEventHandler? = null,
    )
}