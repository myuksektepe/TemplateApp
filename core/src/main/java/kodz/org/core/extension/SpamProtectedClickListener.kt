package kodz.org.core.extension

import android.view.View


/**
 * Created by Murat Yüksektepe on 19.12.2022.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
open class SpamProtectedClickListener(
    private var delay: Long?,
) : View.OnClickListener {

    companion object {
        const val DEFAULT_DELAY = 1000L
    }

    override fun onClick(v: View) {
        v.isEnabled = false
        v.postDelayed({
            v.isEnabled = true
        }, delay ?: DEFAULT_DELAY)
    }
}