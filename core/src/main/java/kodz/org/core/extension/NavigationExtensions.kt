package kodz.org.core.extension

import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavDirections

/**
 * Created by Murat Yüksektepe on 23.12.2022.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */

fun NavController.safeNavigate(direction: NavDirections? = null) {
    direction?.let {
        currentDestination?.getAction(direction.actionId)?.run {
            navigate(it)
        }
    }
}

fun NavController.safeNavigate(@IdRes actionId: Int? = null) {
    actionId?.let {
        currentDestination?.getAction(actionId)?.run {
            navigate(it)
        }
    }
}

fun NavController.safeNavigate(
    @IdRes destinationId: Int,
    @IdRes id: Int
) {
    if (currentDestination?.id != destinationId) {
        navigate(id)
    }
}