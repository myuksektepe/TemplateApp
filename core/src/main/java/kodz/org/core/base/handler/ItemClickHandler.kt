package kodz.org.core.base.handler

import kodz.org.core.model.ClickEventModel

interface ItemClickHandler : BaseEventHandler {
    fun onItemClick(clickEventModel: ClickEventModel?)
}