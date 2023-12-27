package kodz.org.core.extension

import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.ViewDataBinding


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 10.11.2023.
 */
fun ViewDataBinding.makeSlidable(): ViewDataBinding {
    val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    this.root.run {
        layoutParams = params
    }
    return this
}