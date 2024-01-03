package kodz.org.core_ui.row.item_rows.box

import android.graphics.Color
import androidx.databinding.ViewDataBinding
import kodz.org.core.GlideApp
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.contractor.BaseItemRowContractor
import kodz.org.core.common.consts.TWO
import kodz.org.core.extension.makeSlidable
import kodz.org.core.extension.prepareForGroup
import kodz.org.core.extension.setSpamProtectedClickListener
import kodz.org.core.extension.toColor
import kodz.org.core_ui.row.databinding.RowBoxBinding

class BoxRowContractor(
    override val isInCarousel: Boolean? = null,
    override val isInList: Boolean? = null
) : BaseItemRowContractor() {
    override var itemClickHandler: ItemClickHandler? = null
    override var viewBinding: ViewDataBinding? = null
    lateinit var binding: RowBoxBinding
    override fun initBinding(viewDataBinding: ViewDataBinding) {
        viewBinding = if (isInCarousel == true) viewDataBinding.makeSlidable() else viewDataBinding
        binding = (viewBinding as RowBoxBinding)
        initRow()
    }

    private fun initRow() {
        binding.run {
            data?.let { data ->

                // Square or Rectangle?
                rowBoxCard.run {

                    // Paddings
                    this.prepareForGroup(isInList, isInCarousel)
                    if (isInList == true) {
                        // Height - Width
                        val deviceWidth = context.resources.displayMetrics.run { widthPixels }
                        val pageLeftRightSpace = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._16sdp)
                        val itemSpace = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._8sdp)

                        if (data.boxType == BoxType.RECTANGLE) {
                            layoutParams.height = (deviceWidth - pageLeftRightSpace)
                        } else {
                            layoutParams.height = ((deviceWidth - pageLeftRightSpace) / TWO)
                        }
                    }

                    // ----------------------------
                    // Background Color
                    data.backgroundColor?.let {
                        run {
                            it.toColor()?.let { bgColor -> setCardBackgroundColor(bgColor) }
                        }
                    }

                    // Background Image
                    data.backgroundImageUrl?.let {
                        GlideApp.with(imgRowBoxBackground.context)
                            .load(it)
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
                    setSpamProtectedClickListener {
                        itemClickHandler?.onItemClick(data.itemClickEventModel)
                    }
                }
            }
        }
    }
}