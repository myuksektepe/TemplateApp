package kodz.org.core_ui.component.image

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import kodz.org.core.GlideApp
import kodz.org.core_ui.component.R


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 13.10.2023.
 */
@SuppressLint("UseCompatLoadingForDrawables", "RestrictedApi")
class CircleImage @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {
    private var cardView: CardView
    private var imageView: ImageView

    init {
        inflate(context, R.layout.component_circle_image, this)

        cardView = findViewById<CardView>(R.id.crdCircle)
        imageView = findViewById<ImageView>(R.id.imgCircle)

        // Attributes
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CircleImage)

        val imageUrl = attributes.getString(R.styleable.CircleImage_imageUrl)
        val imageDrawable = attributes.getDrawable(R.styleable.CircleImage_imageDrawable)
        val showShadow = attributes.getBoolean(R.styleable.CircleImage_showShadow, false)
        val shadowColor = attributes.getColor(R.styleable.CircleImage_shadowColor, resources.getColor(kodz.org.core.R.color.black80))

        // CardView
        cardView.setCardBackgroundColor(resources.getColor(kodz.org.core.R.color.transparent))
        if (showShadow) {
            cardView.cardElevation = 16f
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                cardView.outlineAmbientShadowColor = shadowColor
                cardView.outlineSpotShadowColor = shadowColor
            }
        } else {
            cardView.cardElevation = 0f
        }


        // ImageView
        if (!imageUrl.isNullOrEmpty()) {
            setImageUrl(imageUrl)
        } else if (imageDrawable != null) {
            setImageDrawable(imageDrawable)
        }

        attributes.recycle()
    }

    fun setImageUrl(url: String) {
        val glideRequest = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .transform(CenterCrop())
            .override(200, 200)

        GlideApp.with(context)
            .load(url)
            .placeholder(kodz.org.core.R.drawable.placeholder)
            .apply(glideRequest)
            .into(imageView)
    }

    fun setImageDrawable(drawable: Drawable) {
        imageView.setImageDrawable(drawable)
    }

    fun setImageDrawable(drawable: Int) {
        val d = resources.getDrawable(drawable)
        imageView.setImageDrawable(d)
    }
}