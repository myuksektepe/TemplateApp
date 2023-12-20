package kodz.org.core_ui.component.button

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import kodz.org.core.common.EMPTY
import kodz.org.core.common.FORTY_FIVE
import kodz.org.core.common.NORMAL
import kodz.org.core.common.ZERO
import kodz.org.core.extension.gone
import kodz.org.core.extension.visible
import kodz.org.core.model.TextWeightType
import kodz.org.core_ui.component.R
import kodz.org.core_ui.component.text.ClassicTextView


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 26.10.2023.
 */
@SuppressLint("UseCompatLoadingForDrawables")
class MultipleButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private lateinit var rootView: ConstraintLayout
    private lateinit var cardView: CardView
    private lateinit var iconView: ImageView
    private lateinit var textView: ClassicTextView
    private lateinit var underlineView: View
    private lateinit var dividerView: View

    private var _iconDrawable: Drawable? = null
    private var _text: String? = EMPTY
    private var _textSize: Int = FORTY_FIVE
    private var _textWeight: String? = NORMAL
    private var _textColor: Int = ZERO
    private var _backgroundColor: Int? = ZERO
    private var _outlineColor: Int? = ZERO
    private var _showOutline: Boolean? = false
    private var _underline: Boolean? = false
    private var _matchParent: Boolean? = true
    private var onClickListener: OnClickListener? = null

    init {
        inflate(context, R.layout.component_multiple_button, this)

        // UI Elements
        rootView = findViewById<ConstraintLayout>(R.id.cnstMultipleButton)
        cardView = findViewById<CardView>(R.id.crdMultipleButton)
        iconView = findViewById<ImageView>(R.id.imgMultipleButton)
        textView = findViewById<ClassicTextView>(R.id.txtMultipleButton)
        dividerView = findViewById<View>(R.id.dividerMultipleButton)
        underlineView = findViewById<View>(R.id.underlineMultipleButton)

        // Attributes
        context.obtainStyledAttributes(attrs, R.styleable.MultipleButton).run {

            // Init Attributes
            _iconDrawable = getDrawable(R.styleable.MultipleButton_iconDrawable)
            _text = getString(R.styleable.MultipleButton_text)
            _textSize = getDimensionPixelSize(R.styleable.MultipleButton_textSize, 16)
            _textWeight = getString(R.styleable.MultipleButton_textWeight)
            _textColor = getColor(R.styleable.MultipleButton_textColor, resources.getColor(android.R.color.white))
            _backgroundColor = getColor(R.styleable.MultipleButton_backgroundColor, resources.getColor(kodz.org.core.R.color.transparent))
            _outlineColor = getColor(R.styleable.MultipleButton_outlineColor, resources.getColor(kodz.org.core.R.color.transparent))
            _showOutline = getBoolean(R.styleable.MultipleButton_showOutline, false)
            _underline = getBoolean(R.styleable.MultipleButton_underline, false)
            _matchParent = getBoolean(R.styleable.MultipleButton_matchParent, false)

            // Icon
            _iconDrawable?.run {
                iconView.setImageDrawable(this)
                iconView.visible()
                dividerView.visible()
            } ?: kotlin.run {
                iconView.gone()
                dividerView.gone()
            }

            // TextView
            textView.run {
                // Text
                _text?.let { text = it }

                // TextSize
                setTextSize(TypedValue.COMPLEX_UNIT_SP, _textSize.toFloat())

                // TextWeight
                _textWeight?.let { setTextWeight(it) }

                // Text Color
                setTextColor(_textColor)
                visible()
            }

            // CardView
            cardView.run {

                // Background
                _backgroundColor?.let {
                    setCardBackgroundColor(it)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        outlineAmbientShadowColor = it
                        outlineSpotShadowColor = it
                    }
                }

                // Match Parent
                _matchParent?.let {
                    layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                } ?: kotlin.run {
                    layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                }

                // Outline
                _outlineColor?.let {
                    val border = resources.getDrawable(kodz.org.core.R.drawable.outlined_button_background)
                    border.setColorFilter(it, PorterDuff.Mode.SRC_ATOP)
                    if (_showOutline == true) background = border
                }
            }

            // Underline
            underlineView.run {
                underlineView.setBackgroundColor(_textColor)
                if (_underline == true) visible() else gone()
            }

            recycle()
        }

        cardView.setOnClickListener {
            onClickListener?.onClick(it)
        }
    }

    override fun setOnClickListener(l: OnClickListener?) {
        onClickListener = l
    }

    /*
    =========================================
    ICON
    */
    fun setIcon(iconDrawable: Drawable?) {
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

    fun setIconColor(color: Int) {
        iconView.setColorFilter(color)
    }

    /*
    =========================================
    TEXT
    */
    fun setText(text: String) {
        textView.text = text
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun setTextColor(color: Int) {
        textView.setTextColor(color)
        underlineView.setBackgroundColor(color)
    }

    fun setTextWeight(weightType: TextWeightType) {
        textView.setTextWeight(weightType.name)
    }

    fun setTextSize(textSize: Int) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toFloat())
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun showOutline(color: Int) {
        cardView.run {
            val border = resources.getDrawable(kodz.org.core.R.drawable.outlined_button_background)
            border.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
            background = border
        }
    }

    /*
    =========================================
    BACKGROUND
    */
    fun setBgColor(color: Int?) {
        val bgColor = color ?: Color.TRANSPARENT
        cardView.run {
            setCardBackgroundColor(bgColor)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                outlineAmbientShadowColor = bgColor
                outlineSpotShadowColor = bgColor
                outlineProvider = ViewOutlineProvider.BACKGROUND
            }
        }
    }

    /*
    =========================================
    UNDERLINE
    */
    fun showUnderline(visible: Boolean) {
        if (visible) underlineView.visible() else underlineView.gone()
    }

    /*
    =========================================
    MATCH PARENT
    */
    fun setMatchParent(matchParent: Boolean) {
        cardView.layoutParams = if (matchParent) {
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        } else {
            LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        }
    }

    /*
    =========================================
    ENABLE / DISABLE
    */
    fun setEnable() {
        this.isEnabled = true
        this.alpha = 1f
        _backgroundColor?.let {
            cardView.setCardBackgroundColor(it)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                outlineAmbientShadowColor = it
                outlineSpotShadowColor = it
            }
        }
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
}