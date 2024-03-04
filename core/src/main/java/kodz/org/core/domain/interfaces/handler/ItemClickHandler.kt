package kodz.org.core.domain.interfaces.handler

import kodz.org.core.domain.models.ClickEventModel

interface ItemClickHandler : BaseEventHandler {
    fun onItemClick(clickEventModel: ClickEventModel?)
}