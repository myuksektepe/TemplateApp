package kodz.org.core.base.handler

import kodz.org.core.model.ItemClickEventModel

interface ItemClickHandler : BaseEventHandler {
    fun onItemClick(itemClickEventModel: ItemClickEventModel?)
}