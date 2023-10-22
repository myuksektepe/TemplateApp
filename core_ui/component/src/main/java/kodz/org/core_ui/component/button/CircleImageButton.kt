package kodz.org.core_ui.component.button

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import kodz.org.core_ui.component.R


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 11.10.2023.
 */
@SuppressLint("UseCompatLoadingForDrawables", "RestrictedApi")
class CircleImageButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {
    lateinit var cardView: ConstraintLayout
    lateinit var imageView: ImageView

    init {
        inflate(context, R.layout.component_circle_button, this)

        cardView = findViewById<ConstraintLayout>(R.id.crdCircleButton)
        imageView = findViewById<AppCompatImageView>(R.id.imgCircleButton)

        // Attributes
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CircleButton)

        // Init Attributes
        val backgroundColor = attributes.getColor(R.styleable.CircleButton_backgroundColor, resources.getColor(kodz.org.core.R.color.white))
        val iconDrawable = attributes.getDrawable(R.styleable.CircleButton_iconDrawable)
        val iconColor = attributes.getColor(R.styleable.CircleButton_iconColor, resources.getColor(kodz.org.core.R.color.orange))
        val iconPadding = attributes.getDimensionPixelSize(R.styleable.CircleButton_iconPadding, 10)

        // CardView
        cardView.run {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        }

        // Icon View
        imageView.run {
            setImageDrawable(iconDrawable)
            setColorFilter(iconColor)

            val colorList = ColorStateList.valueOf(backgroundColor)
            backgroundTintList = colorList

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                outlineAmbientShadowColor = if (backgroundColor == resources.getColor(kodz.org.core.R.color.white)) iconColor else backgroundColor
                outlineSpotShadowColor = if (backgroundColor == resources.getColor(kodz.org.core.R.color.white)) iconColor else backgroundColor
            }

            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT).apply {
                setPadding(iconPadding, iconPadding, iconPadding, iconPadding)
            }
        }

        attributes.recycle()
    }

    fun setIconDrawable(icon: Drawable) {
        imageView.setImageDrawable(icon)
    }
}