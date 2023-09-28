package kodz.org.core.component

import android.util.Log
import kodz.org.core.base.component.ComponentBaseContractor
import kodz.org.core.base.component.ComponentBaseDataModel
import kodz.org.core.base.component.ComponentBaseRow
import kodz.org.core.base.data.http.toResponseModel
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.model.ClickEventModel
import kotlin.reflect.full.primaryConstructor

inline fun <reified R : ComponentBaseRow, reified C : ComponentBaseContractor, reified BDM : ComponentBaseDataModel> makeRow(dataModelString: String?): ComponentBaseRow? {
    var row: ComponentBaseRow? = null
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

inline fun <reified R : ComponentBaseRow, reified C : ComponentBaseContractor, reified BDM : ComponentBaseDataModel> makeRow(dataModel: ComponentBaseDataModel?): ComponentBaseRow? {
    var row: ComponentBaseRow? = null
    row = R::class.primaryConstructor?.call(dataModel)?.apply {
        (component as? C)?.eventHandler = object : ItemClickHandler {
            override fun onItemClick(clickEventModel: ClickEventModel?) {
                Log.i("applog", clickEventModel.toString())
            }
        }
    }
    return row
}