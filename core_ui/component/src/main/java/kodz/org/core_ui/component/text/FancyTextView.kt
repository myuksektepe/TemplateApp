package kodz.org.core_ui.component.text

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import kodz.org.core_ui.component.R

/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 12.10.2023.
 */
@SuppressLint("UseCompatLoadingForDrawables", "RestrictedApi")
class FancyTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
) : AppCompatTextView(context, attrs, defStyle) {
    init {
        inflate(context, R.layout.component_fancy_text_view, null)
        attrs?.let {
            // textSize = 18F
            // setAutoSizeTextTypeUniformWithConfiguration(18, 48, 2, TypedValue.COMPLEX_UNIT_DIP)
            typeface = ResourcesCompat.getFont(context, R.font.kg)
        }
    }
}