package kodz.org.core.component.carousel

import kodz.org.core.base.component.GenerateBaseRow
import kodz.org.core.base.component.BaseRow
import kodz.org.core.component.carousel_item.CarouselItemEventHandler

class GenerateCarouselRow : GenerateBaseRow<GenerateCarouselRow.Params>() {
    override fun execute(params: Params): BaseRow =
        CarouselRow(params.dataModel).apply {
            component.eventHandler = params.eventHandler
        }

    data class Params(
        val dataModel: CarouselDataModel,
        val eventHandler: CarouselItemEventHandler? = null
    )
}