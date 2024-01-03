package kodz.org.core.extension

import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.core.view.setMargins
import kodz.org.core.common.consts.ZERO


/**
 * Created by Murat Yüksektepe on 19.12.2022.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
fun View.isVisible() = this.visibility == View.VISIBLE

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.setIsVisible(isVisible: Boolean) {
    if (isVisible) this.visible() else this.gone()
}

fun View.setDisable() {
    this.isFocusable = false
    this.isClickable = false
    this.isEnabled = false
}

fun View.setEnable() {
    this.isFocusable = true
    this.isClickable = true
    this.isEnabled = true
}

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

fun View.setSpamProtectedClickListener(timeDelay: Long? = 1000L, onSafeClick: (View) -> Unit) {
    val safeClickListener =
        object : SpamProtectedClickListener(timeDelay) {
            override fun onClick(v: View) {
                super.onClick(v)
                onSafeClick(v)
            }
        }
    setOnClickListener(safeClickListener)
}

fun View.setUnClickable() {
    this.isClickable = false
    this.isFocusable = false
    if (this is ViewGroup) {
        for (i in 0 until this.childCount) {
            this.getChildAt(i).setUnClickable()
        }
    }
}

fun View.setClickable() {
    this.isClickable = true
    this.isFocusable = true
    if (this is ViewGroup) {
        for (i in 0 until this.childCount) {
            this.getChildAt(i).setUnClickable()
        }
    }
}

inline fun <T : View> T.afterMeasured(crossinline f: T.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredWidth > 0 && measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                f()
            }
        }
    })
}

fun Window.lockScreenToUserInteractions() {
    this.setFlags(
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
    )
}

fun Window.unLockScreenToUserInteractions() {
    this.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
}

fun View.prepareForGroup(isInList: Boolean?, isInCarousel: Boolean?) {
    if (isInList == true) {
        val mLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(ZERO)
        }
        this.run {
            setPadding(ZERO, ZERO, ZERO, ZERO)
            layoutParams = mLayoutParams
        }
    }
}