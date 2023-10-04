package kodz.org.core.base.handler

import kodz.org.core.model.screen.ClickEventModel

interface ItemClickHandler : BaseEventHandler {
    fun onItemClick(clickEventModel: ClickEventModel?)
}