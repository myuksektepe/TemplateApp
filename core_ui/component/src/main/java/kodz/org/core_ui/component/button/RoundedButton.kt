package kodz.org.core_ui.component.button

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import kodz.org.core.model.TextWeightType
import kodz.org.core_ui.component.R
import kodz.org.core_ui.component.text.ClassicTextView


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 12.10.2023.
 */
@SuppressLint("UseCompatLoadingForDrawables", "RestrictedApi")
class RoundedButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle), View.OnClickListener {

    lateinit var cardView: CardView
    lateinit var iconView: ImageView
    lateinit var textView: ClassicTextView
    lateinit var dividerView: View
    private var _backgroundColor: Int = 0
    private var _onClickListener: OnClickListener? = null
    private var _callback: Function0<Unit>? = null

    init {
        inflate(context, R.layout.component_rounded_button, this)

        super.setOnClickListener(this)

        // UI Elements
        cardView = findViewById<CardView>(R.id.crdRoundedButton)
        iconView = findViewById<ImageView>(R.id.imgRoundedButton)
        textView = findViewById<ClassicTextView>(R.id.txtRoundedButton)
        dividerView = findViewById<View>(R.id.divider)

        // Attributes
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.RoundedButton)

        // Init Attributes
        val matchParent = attributes.getBoolean(R.styleable.RoundedButton_matchParent, false)
        _backgroundColor = attributes.getColor(R.styleable.RoundedButton_backgroundColor, resources.getColor(kodz.org.core.R.color.green))
        var iconVisibility = false
        val iconDrawable = attributes.getDrawable(R.styleable.RoundedButton_iconDrawable)
        if (iconDrawable != null) iconVisibility = true
        val buttonText = attributes.getString(R.styleable.RoundedButton_text)
        val textSize = attributes.getDimensionPixelSize(R.styleable.RoundedButton_textSize, 50)

        // CardView
        cardView.run {
            setCardBackgroundColor(_backgroundColor)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                outlineAmbientShadowColor = _backgroundColor
                outlineSpotShadowColor = _backgroundColor
            }
            if (matchParent) {
                layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            }
        }

        // Icon View
        iconView.run {
            setImageDrawable(iconDrawable)
            visibility = if (iconVisibility) VISIBLE else GONE
            dividerView.visibility = if (iconVisibility) VISIBLE else GONE
        }

        // TextView
        textView.run {
            text = buttonText
            setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toFloat())
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

    fun setBgColor(color: Int) {
        cardView.setCardBackgroundColor(color)
    }

    fun setIconColor(color: Int) {
        iconView.setColorFilter(color)
    }

    fun setDisable() {
        val backgroundColor = resources.getColor(kodz.org.core.R.color.lightGray)
        this.isEnabled = false
        this.alpha = 0.7f
        cardView.setCardBackgroundColor(backgroundColor)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            outlineAmbientShadowColor = backgroundColor
            outlineSpotShadowColor = backgroundColor
        }
    }

    fun setEnable() {
        this.isEnabled = true
        this.alpha = 1f
        cardView.setCardBackgroundColor(_backgroundColor)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            outlineAmbientShadowColor = _backgroundColor
            outlineSpotShadowColor = _backgroundColor
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