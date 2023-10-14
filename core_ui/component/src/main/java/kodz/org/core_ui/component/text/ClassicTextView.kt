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
class ClassicTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
) : AppCompatTextView(context, attrs, defStyle) {

    init {
        inflate(context, R.layout.component_classic_text_view, null)

        // Attributes
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.ClassicTextView)

        // Init attributes
        when (attributes.getInt(R.styleable.ClassicTextView_weight, 1)) {
            0 -> {
                typeface = ResourcesCompat.getFont(context, R.font.averta_light)
            }
            1 -> {
                typeface = ResourcesCompat.getFont(context, R.font.averta_regular)
            }
            2 -> {
                typeface = ResourcesCompat.getFont(context, R.font.averta_bold)
            }
        }

        when (attributes.getInt(R.styleable.ClassicTextView_align, 1)) {
            1 -> {
                textAlignment = TEXT_ALIGNMENT_TEXT_START
            }
            2 -> {
                textAlignment = TEXT_ALIGNMENT_CENTER
            }
            3 -> {
                textAlignment = TEXT_ALIGNMENT_TEXT_END
            }
        }

        if (attributes.getBoolean(R.styleable.ClassicTextView_showShadow, false)) {
            setShadowLayer(8f, 0f, 2f, kodz.org.core.R.color.black80)
        }

        attributes.recycle()
    }

}