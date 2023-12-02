package kodz.org.core.extension

import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.ViewDataBinding


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 10.11.2023.
 */
fun ViewDataBinding.makeSlidable(): ViewDataBinding {
    val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    // params.setMargins(this.root.context.resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._16sdp))
    this.root.run {
        // layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        layoutParams = params
    }
    return this
}