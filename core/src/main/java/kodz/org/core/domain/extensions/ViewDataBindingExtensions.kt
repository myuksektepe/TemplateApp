package kodz.org.core.domain.extensions

import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.setMargins
import androidx.core.view.updateMargins
import androidx.databinding.ViewDataBinding

/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 10.11.2023.
 */
fun ViewDataBinding.makeSlidable(): ViewDataBinding {
    val defaultMarginDimensionBottom = this.root.marginBottom
    val defaultMarginDimensionLeft = this.root.marginLeft
    val marginDimension = this.root.context.resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._16sdp)
    val layoutParams = LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT
    )

    /**
     * Bir row'u carousel içine koyabilmek için yüksekliğini MATCH_PARENT yapmamız gerekiyor.
     * Bunu yaparken eğer o row'un root elemanında margin değerleri varsa, siliniyor.
     * Bu yüzden margin'i olan bir row'u carousel içine koyarken yeniden margin vermek zorunda kalıyoruz.
     */

    if (defaultMarginDimensionLeft != null || defaultMarginDimensionLeft != 0) {
        layoutParams.updateMargins(
            left = defaultMarginDimensionLeft,
            right = defaultMarginDimensionLeft,
            bottom = defaultMarginDimensionBottom,
            top = defaultMarginDimensionBottom
        )
    } else {
        layoutParams.setMargins(marginDimension)
    }

    this.root.run {
        this.layoutParams = layoutParams
    }

    return this
}