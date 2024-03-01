package kodz.org.core.base.handler

import kodz.org.core.domain.models.ClickEventModel

interface ItemClickHandler : BaseEventHandler {
    fun onItemClick(clickEventModel: ClickEventModel?)
}