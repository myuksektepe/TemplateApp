package kodz.org.core_ui.component.button

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import kodz.org.core.extension.gone
import kodz.org.core.extension.visible
import kodz.org.core.model.TextWeightType
import kodz.org.core_ui.component.R
import kodz.org.core_ui.component.text.ClassicTextView


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 15.10.2023.
 */

@SuppressLint("UseCompatLoadingForDrawables", "RestrictedApi")
class TextButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle), View.OnClickListener {
    private lateinit var iconView: ImageView
    private lateinit var textView: ClassicTextView
    private lateinit var underline: View
    private lateinit var dividerView: View
    private var _textColor: Int = 0
    private var _underline: Boolean = false
    private var _onClickListener: OnClickListener? = null
    private var _callback: Function0<Unit>? = null

    init {
        inflate(context, R.layout.component_text_button, this)
        super.setOnClickListener(this)

        // UI Elements
        iconView = findViewById<ImageView>(R.id.imgTextButton)
        textView = findViewById<ClassicTextView>(R.id.txtTextButton)
        underline = findViewById<View>(R.id.underline)
        dividerView = findViewById<View>(R.id.divider)

        // Attributes
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.TextButton)

        // Init Attributes
        _textColor = attributes.getColor(R.styleable.TextButton_textColor, resources.getColor(kodz.org.core.R.color.black))
        _underline = attributes.getBoolean(R.styleable.TextButton_underline, false)
        var iconVisibility = false
        val iconDrawable = attributes.getDrawable(R.styleable.TextButton_iconDrawable)
        if (iconDrawable != null) iconVisibility = true
        val buttonText = attributes.getString(R.styleable.TextButton_text)
        val textSize = attributes.getDimensionPixelSize(R.styleable.RoundedButton_textSize, 45)

        // Icon View
        iconView.run {
            setImageDrawable(iconDrawable)
            visibility = if (iconVisibility) VISIBLE else GONE
            dividerView.visibility = if (iconVisibility) VISIBLE else GONE
        }

        // TextView
        textView.run {
            text = buttonText
            setTextColor(_textColor)
            setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toFloat())
        }

        // Underline
        underline.run {
            visibility = if (_underline) View.VISIBLE else View.GONE
        }

        attributes.recycle()

    }


    fun setText(text: String) {
        textView.text = text
    }

    fun setTextColor(color: Int) {
        textView.setTextColor(color)
    }

    fun setTextWeight(weightType: TextWeightType) {
        when (weightType) {
            TextWeightType.THIN -> textView.setTextWeight(0)
            TextWeightType.NORMAL -> textView.setTextWeight(1)
            TextWeightType.BOLD -> textView.setTextWeight(2)
        }
    }

    fun setIconColor(color: Int) {
        iconView.setColorFilter(color)
    }

    fun setUnderlineColor(color: Int) {
        underline.setBackgroundColor(color)
    }

    fun underline(visible: Boolean) {
        if (visible) underline.visible() else underline.gone()
    }

    fun setDisable() {
        val backgroundColor = resources.getColor(kodz.org.core.R.color.lightGray)
        this.isEnabled = false
        this.alpha = 0.7f
        textView.setTextColor(backgroundColor)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            outlineAmbientShadowColor = backgroundColor
            outlineSpotShadowColor = backgroundColor
        }
    }

    fun setEnable() {
        this.isEnabled = true
        this.alpha = 1f
        textView.setTextColor(_textColor)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            outlineAmbientShadowColor = _textColor
            outlineSpotShadowColor = _textColor
        }
    }

    fun setIcon(iconDrawable: Drawable?) {
        // Icon View
        iconView.run {
            iconDrawable?.let {
                setImageDrawable(iconDrawable)
                visibility = VISIBLE
                dividerView.visibility = VISIBLE
            } ?: kotlin.run {
                visibility = GONE
                dividerView.visibility = GONE
            }
        }
    }

    fun setCallback(unit: Function0<Unit>?) {
        unit?.let {
            _callback = it
        }
    }

    override fun onClick(view: View?) {
        _onClickListener?.onClick(view)
        _callback?.invoke()
    }

    override fun setOnClickListener(l: OnClickListener?) {
        _onClickListener = l
    }
}