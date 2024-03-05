package kodz.org.core_ui.row.item_rows.box

import android.graphics.Color
import androidx.databinding.ViewDataBinding
import kodz.org.core.GlideApp
import kodz.org.core.base.row.contractor.BaseItemRowContractor
import kodz.org.core.domain.consts.TWO
import kodz.org.core.domain.extensions.gone
import kodz.org.core.domain.extensions.makeSlidable
import kodz.org.core.domain.extensions.prepareForGroup
import kodz.org.core.domain.extensions.setSpamProtectedClickListener
import kodz.org.core.domain.extensions.toColor
import kodz.org.core.domain.interfaces.handler.ItemClickHandler
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
                    data.title?.let {
                        txtRowBoxTitle.text = it
                    } ?: {
                        txtRowBoxTitle.gone()
                    }

                    // Description
                    data.description?.let {
                        txtRowBoxDescription.text = it
                    } ?: { txtRowBoxDescription.gone() }

                    // OnClick
                    setSpamProtectedClickListener {
                        itemClickHandler?.onItemClick(data.clickEventModel)
                    }
                }
            }
        }
    }
}