package kodz.org.core_ui.row.common

import kodz.org.core.base.data.http.toResponseModel
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.BaseRow
import kodz.org.core.base.row.BaseRowContractor
import kodz.org.core.base.row.BaseRowDataModel
import kotlin.reflect.full.primaryConstructor

inline fun <reified R : BaseRow, reified C : BaseRowContractor, reified BDM : BaseRowDataModel> makeRow(
    dataModelString: String?,
    itemClickHandler: ItemClickHandler? = null
): BaseRow? {
    return dataModelString?.toResponseModel<BDM>()?.let { dataModel ->
        return R::class.primaryConstructor?.call(dataModel, null)?.apply {
            (contractor as? C)?.itemClickHandler = itemClickHandler
        }
    }
}

inline fun <reified R : BaseRow, reified C : BaseRowContractor> makeRow(
    dataModel: BaseRowDataModel?,
    itemClickHandler: ItemClickHandler? = null
): BaseRow? {
    return R::class.primaryConstructor?.call(dataModel)?.apply {
        (contractor as? C)?.itemClickHandler = itemClickHandler
    }
}