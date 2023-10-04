package kodz.org.core.component

import kodz.org.core.base.component.ComponentBaseContractor
import kodz.org.core.base.component.ComponentBaseDataModel
import kodz.org.core.base.component.ComponentBaseRow
import kodz.org.core.base.data.http.toResponseModel
import kodz.org.core.base.handler.ItemClickHandler
import kotlin.reflect.full.primaryConstructor

inline fun <reified R : ComponentBaseRow, reified C : ComponentBaseContractor, reified BDM : ComponentBaseDataModel> makeRow(
    dataModelString: String?,
    itemClickHandler: ItemClickHandler? = null
): ComponentBaseRow? {
    return dataModelString?.toResponseModel<BDM>()?.let { dataModel ->
        return R::class.primaryConstructor?.call(dataModel)?.apply {
            (contractor as? C)?.itemClickHandler = itemClickHandler
        }
    }
}

inline fun <reified R : ComponentBaseRow, reified C : ComponentBaseContractor> makeRow(
    dataModel: ComponentBaseDataModel?,
    itemClickHandler: ItemClickHandler? = null
): ComponentBaseRow? {
    return R::class.primaryConstructor?.call(dataModel)?.apply {
        (contractor as? C)?.itemClickHandler = itemClickHandler
    }
}