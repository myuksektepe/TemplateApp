package kodz.org.core.component.carousel_item

import kodz.org.core.base.component.GenerateBaseRow
import kodz.org.core.base.component.BaseRow

class GenerateCarouselItemRow : GenerateBaseRow<GenerateCarouselItemRow.Params>() {

    override fun execute(params: Params): BaseRow =
        CarouselItemRow(params.dataModel).apply {
            component.eventHandler = params.eventHandler
        }

    data class Params(
        val dataModel: CarouselItemDataModel,
        val eventHandler: CarouselItemEventHandler
    )
}