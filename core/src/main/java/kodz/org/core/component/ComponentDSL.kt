package kodz.org.core.component

import android.util.Log
import kodz.org.core.base.component.ComponentBaseContractor
import kodz.org.core.base.component.ComponentBaseDataModel
import kodz.org.core.base.component.ComponentBaseRow
import kodz.org.core.base.data.http.toResponseModel
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.model.ClickEventModel
import kotlin.reflect.full.primaryConstructor

inline fun <reified R : ComponentBaseRow, reified C : ComponentBaseContractor, reified BDM : ComponentBaseDataModel> makeRow(
    dataModelString: String?
): ComponentBaseRow? {
    return dataModelString?.toResponseModel<BDM>()?.let { dataModel ->
        return R::class.primaryConstructor?.call(dataModel)?.apply {
            (contractor as? C)?.eventHandler = object : ItemClickHandler {
                override fun onItemClick(clickEventModel: ClickEventModel?) {
                    Log.i("applog", clickEventModel.toString())
                }
            }
        }
    }
}

inline fun <reified R : ComponentBaseRow, reified C : ComponentBaseContractor> makeRow(
    dataModel: ComponentBaseDataModel?
): ComponentBaseRow? {
    return R::class.primaryConstructor?.call(dataModel)?.apply {
        (contractor as? C)?.eventHandler = object : ItemClickHandler {
            override fun onItemClick(clickEventModel: ClickEventModel?) {
                Log.i("applog", clickEventModel.toString())
            }
        }
    }
}