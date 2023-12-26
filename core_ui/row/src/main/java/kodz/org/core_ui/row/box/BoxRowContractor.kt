package kodz.org.core_ui.row.box

import android.graphics.Color
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import kodz.org.core.R
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.BaseRowContractor
import kodz.org.core.extension.setSpamProtectedClickListener
import kodz.org.core.extension.toColor
import kodz.org.core_ui.row.databinding.RowBoxBinding

class BoxRowContractor : BaseRowContractor() {
    override var itemClickHandler: ItemClickHandler? = null
    override var viewBinding: ViewDataBinding? = null
    lateinit var binding: RowBoxBinding
    override fun initBinding(viewDataBinding: ViewDataBinding) {
        viewBinding = viewDataBinding
        binding = (viewBinding as RowBoxBinding)
        initRow()
    }

    private fun initRow() {
        binding.run {
            data?.let { data ->

                // Square or Rectangle?
                rowBoxRoot.run {
                    val deviceWidth = rowBoxRoot.context.resources.displayMetrics.run { widthPixels }
                    if (data.boxType == BoxType.RECTANGLE) {
                        rowBoxRoot.layoutParams.height = deviceWidth
                    } else {
                        rowBoxRoot.layoutParams.height = (deviceWidth / 2)
                    }

                    // ----------------------------
                    // Background Color
                    data.backgroundColor?.let {
                        rowBoxRoot.run {
                            it.toColor()?.let { bgColor -> setCardBackgroundColor(bgColor) }
                        }
                    }

                    // Background Image
                    data.backgroundImageUrl?.let {
                        Glide.with(imgRowBoxBackground.context)
                            .load(it)
                            .centerCrop()
                            //.diskCacheStrategy(DiskCacheStrategy.NONE)
                            .error(R.drawable.placeholder)
                            .into(imgRowBoxBackground)
                    }

                    // Text Color
                    if (data.gradientIsVisible == true) {
                        txtRowBoxTitle.setTextColor(Color.WHITE)
                        txtRowBoxDescription.setTextColor(Color.WHITE)
                    } else {
                        data.textColor?.let { color ->
                            color.toColor()?.let { textColor ->
                                txtRowBoxTitle.setTextColor(textColor)
                                txtRowBoxDescription.setTextColor(textColor)
                            }
                        }
                    }

                    // Title
                    txtRowBoxTitle.text = data.title

                    // Description
                    txtRowBoxDescription.text = data.description

                    // OnClick
                    rowBoxRoot.setSpamProtectedClickListener {
                        itemClickHandler?.onItemClick(data.itemClickEventModel)
                    }
                }
            }
        }
    }
}