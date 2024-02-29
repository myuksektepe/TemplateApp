package kodz.org.core_ui.row.item_rows.expandable_box

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.databinding.ViewDataBinding
import kodz.org.core.R
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.contractor.BaseItemRowContractor
import kodz.org.core.common.enums.CommonIcons
import kodz.org.core.extension.makeSlidable
import kodz.org.core_ui.extension.sdp
import kodz.org.core_ui.extension.textSdp
import kodz.org.core_ui.row.databinding.RowExpandableBoxBinding
import kodz.org.core_ui.row.item_rows.box.CornersType
import kodz.org.core_ui.ui.TemplateAppTheme
import kodz.org.core_ui.ui.avertaFamily

class ExpandableBoxRowContractor(
    override val isInCarousel: Boolean? = null,
    override val isInList: Boolean? = null
) : BaseItemRowContractor() {
    override var itemClickHandler: ItemClickHandler? = null
    override var viewBinding: ViewDataBinding? = null
    lateinit var binding: RowExpandableBoxBinding

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        viewBinding = if (isInCarousel == true) viewDataBinding.makeSlidable() else viewDataBinding
        binding = (viewBinding as RowExpandableBoxBinding)
        initRow()
    }

    fun initRow() {
        binding.run {
            data?.let { data ->
                composeView.apply {
                    setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                    setContent {

                        val modifier = Modifier
                        if (isInCarousel == true || isInList == true) modifier.fillMaxHeight() else modifier.wrapContentSize()

                        TemplateAppTheme {
                            ExpandableBoxRow(
                                modifier = modifier,
                                data = data,
                                itemClickHandler = itemClickHandler
                            )
                        }

                    }
                }
            }
        }
    }

    @Composable
    fun ExpandableBoxRow(
        modifier: Modifier,
        data: ExpandableBoxRowDataModel,
        itemClickHandler: ItemClickHandler?,
    ) {

        var isExpanded by remember { mutableStateOf(false) }

        Card(
            shape = if (data.cornersType == CornersType.ROUNDED) RoundedCornerShape(8.sdp) else RoundedCornerShape(0.sdp),
            modifier = modifier
                .fillMaxWidth()
                .clickable {
                    isExpanded = !isExpanded
                }
        ) {
            Column(
                modifier.padding(horizontal = 16.sdp, vertical = 8.sdp)
            ) {
                data.title?.let { title ->
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = modifier.weight(1f),
                            text = title,
                            style = TextStyle(
                                fontFamily = avertaFamily,
                                fontSize = 14.textSdp,
                                letterSpacing = 0.sp,
                                fontWeight = FontWeight.Bold,
                            ),
                            maxLines = if (!isExpanded) 2 else 5,
                            overflow = TextOverflow.Ellipsis
                        )
                        Image(
                            modifier = modifier.size(24.sdp),
                            painter = painterResource(
                                (if (!isExpanded) CommonIcons.ARROW_DOWN else CommonIcons.ARROW_UP).resourceId
                            ),
                            colorFilter = if (isSystemInDarkTheme()) ColorFilter.tint(Color.White) else ColorFilter.tint(Color.Black),
                            contentDescription = stringResource(id = R.string.showMore)
                        )
                    }
                    if (isExpanded && !data.description.isNullOrBlank()) {
                        HorizontalDivider(
                            modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.sdp),
                            thickness = 1.sdp,
                            color = colorResource(id = R.color.dayNightReverse)
                        )
                        Text(
                            text = data.description,
                            style = TextStyle(
                                fontFamily = avertaFamily,
                                // fontSize = 14.textSdp,
                                letterSpacing = 0.sp,
                                fontWeight = FontWeight.Normal,
                                lineHeight = 1.5.em
                            )
                        )
                    }
                }
            }
        }
    }

}

