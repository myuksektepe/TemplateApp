package kodz.org.core_ui.component.button

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import kodz.org.core_ui.component.R


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
    lateinit var textView: TextView
    private var _backgroundColor: Int = 0
    private var _onClickListener: OnClickListener? = null
    private var _callback: Function0<Unit>? = null

    init {
        inflate(context, R.layout.component_rounded_button, this)

        super.setOnClickListener(this)

        // UI Elements
        cardView = findViewById<CardView>(R.id.crdRoundedButton)
        iconView = findViewById<ImageView>(R.id.imgRoundedButton)
        textView = findViewById<TextView>(R.id.txtRoundedButton)

        // Attributes
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.RoundedButton)

        // Init Attributes
        val matchParent = attributes.getBoolean(R.styleable.RoundedButton_matchParent, false)
        _backgroundColor = attributes.getColor(R.styleable.RoundedButton_backgroundColor, resources.getColor(kodz.org.core.R.color.green))
        var iconVisibility = false
        val iconDrawable = attributes.getDrawable(R.styleable.RoundedButton_iconDrawable)
        if (iconDrawable != null) iconVisibility = true
        val buttonText = attributes.getString(R.styleable.RoundedButton_text)

        // CardView
        cardView.apply {
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
        iconView.apply {
            setImageDrawable(iconDrawable)
            visibility = if (iconVisibility) VISIBLE else GONE
        }

        // TextView
        textView.apply {
            text = buttonText
        }

        attributes.recycle()

    }

    fun setText(text: String) {
        textView.text = text
    }

    fun setTextColor(color: Int) {
        textView.setTextColor(color)
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
        iconView.apply {
            iconDrawable?.let {
                setImageDrawable(iconDrawable)
                visibility = VISIBLE
            } ?: kotlin.run { visibility = GONE }
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