package kodz.org.core.extension

import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.setMargins
import androidx.databinding.ViewDataBinding


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 10.11.2023.
 */
fun ViewDataBinding.makeSlidable() {
    val params = ConstraintLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
    params.setMargins(this.root.context.resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._16sdp))
    this.root.run {
        layoutParams = params
    }
}