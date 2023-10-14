package kodz.org.core_ui.component.text

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import kodz.org.core_ui.component.R


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 13.10.2023.
 */
@SuppressLint("UseCompatLoadingForDrawables", "RestrictedApi")
class IconTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
) : ConstraintLayout(context, attrs, defStyle) {
    lateinit var imgIcon: ImageView
    lateinit var txtTitle: ClassicTextView
    lateinit var txtNumber: ClassicTextView
    private var numberInt: Int = 0

    init {
        inflate(context, R.layout.component_icon_text_view, this)

        imgIcon = findViewById(R.id.imgIcon)
        txtTitle = findViewById(R.id.txtTitle)
        txtNumber = findViewById(R.id.txtNumber)

        // Attributes
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.IconTextView)

        val iconDrawable = attributes.getDrawable(R.styleable.IconTextView_iconDrawable)
        val iconColor = attributes.getColor(R.styleable.IconTextView_iconColor, ContextCompat.getColor(context, kodz.org.core.R.color.dayNightReverse))
        val titleText = attributes.getText(R.styleable.IconTextView_titleText)
        val numberText = attributes.getInt(R.styleable.IconTextView_numberText, 0)

        // Icon
        imgIcon.setImageDrawable(iconDrawable)
        imgIcon.setColorFilter(iconColor)

        // Title
        txtTitle.text = titleText

        // Number
        txtNumber.text = numberText.toString()
        numberInt = numberText

        attributes.recycle()

    }

    fun setNumberText(number: Int) {
        numberInt = number
        txtNumber.text = number.toString()
    }

    @SuppressLint("SetTextI18n")
    fun numberIncrease() {
        txtNumber.text = (numberInt + 1).toString()
    }
}