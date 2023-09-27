package kodz.org.core.component

import android.util.Log
import kodz.org.core.base.component.BaseComponent
import kodz.org.core.base.component.BaseDataModel
import kodz.org.core.base.component.BaseRow
import kodz.org.core.base.data.http.toResponseModel
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.model.ClickEventModel
import kotlin.reflect.full.primaryConstructor

inline fun <reified R : BaseRow, reified C : BaseComponent, reified BDM : BaseDataModel> makeRow(dataModelString: String?): BaseRow? {
    var row: BaseRow? = null
    dataModelString?.toResponseModel<BDM>()?.let { dataModel ->
        row = R::class.primaryConstructor?.call(dataModel)?.apply {
            (component as? C)?.eventHandler = object : ItemClickHandler {
                override fun onItemClick(clickEventModel: ClickEventModel?) {
                    Log.i("applog", clickEventModel.toString())
                }
            }
        }
    }
    return row
}