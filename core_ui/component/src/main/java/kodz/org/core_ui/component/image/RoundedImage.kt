package kodz.org.core_ui.component.image

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kodz.org.core.GlideApp
import kodz.org.core_ui.component.R


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 13.10.2023.
 */
@SuppressLint("UseCompatLoadingForDrawables", "RestrictedApi")
class RoundedImage @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : CardView(context, attrs, defStyle) {
    lateinit var cardView: CardView
    lateinit var imageView: ImageView

    init {
        inflate(context, R.layout.component_rounded_image, this)

        cardView = findViewById<CardView>(R.id.crdRounded)
        imageView = findViewById<ImageView>(R.id.imgRounded)

        // Attributes
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.RoundedImage)

        val imageUrl = attributes.getString(R.styleable.RoundedImage_imageUrl)
        val imageDrawable = attributes.getDrawable(R.styleable.RoundedImage_imageDrawable)
        val showShadow = attributes.getBoolean(R.styleable.RoundedImage_showShadow, false)
        val shadowColor = attributes.getColor(R.styleable.RoundedImage_shadowColor, resources.getColor(kodz.org.core.R.color.black80))

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
        //imageView.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
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
            //.transform(FitCenter())
            //.override(200, 200)

        GlideApp.with(context)
            .load(url)
            .apply(glideRequest)
            .placeholder(kodz.org.core.R.drawable.placeholder)
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