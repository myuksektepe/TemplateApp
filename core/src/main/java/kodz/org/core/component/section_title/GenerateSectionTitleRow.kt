package kodz.org.core.component.section_title

import kodz.org.core.base.component.GenerateBaseRow
import kodz.org.core.base.component.BaseRow
import kodz.org.core.base.handler.ButtonEventHandler


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