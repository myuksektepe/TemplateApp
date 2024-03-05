package kodz.org.core_ui.row.item_rows.html_view

import android.os.Build
import android.text.Html
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.contractor.BaseItemRowContractor
import kodz.org.core.domain.extensions.makeSlidable
import kodz.org.core.domain.interfaces.handler.ItemClickHandler
import kodz.org.core_ui.extension.toAnnotatedString
import kodz.org.core_ui.row.databinding.RowComposeViewBinding
import kodz.org.core_ui.ui.HtmlView
import kodz.org.core_ui.ui.TemplateAppTheme

class HtmlViewRowContractor(
    override val isInCarousel: Boolean?,
    override val isInList: Boolean?,
) : BaseItemRowContractor() {
    override var itemClickHandler: ItemClickHandler? = null
    override var viewBinding: ViewDataBinding? = null
    lateinit var binding: RowComposeViewBinding

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        viewBinding = if (isInCarousel == true) viewDataBinding.makeSlidable() else viewDataBinding
        binding = (viewBinding as RowComposeViewBinding)
        initRow()
    }

    private fun initRow() {
        binding.run {
            (data as HtmlViewRowDataModel).let { data ->
                composeView.apply {
                    // setViewTreeSavedStateRegistryOwner(this.findViewTreeSavedStateRegistryOwner())
                    // setViewTreeLifecycleOwner(this.findViewTreeLifecycleOwner())
                    //viewBinding?.lifecycleOwner?.let {
                    //    setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnLifecycleDestroyed(it))
                    //}

                    setContent {

                        val modifier = Modifier
                        if (isInCarousel == true || isInList == true) modifier.fillMaxHeight() else modifier.wrapContentSize()

                        TemplateAppTheme {
                            HtmlViewRow(
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
    fun HtmlViewRow(
        modifier: Modifier,
        data: HtmlViewRowDataModel,
        itemClickHandler: ItemClickHandler?
    ) {
        if (!data.html.isNullOrEmpty()) {

            val content = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(data.html, Html.FROM_HTML_MODE_LEGACY)
            } else Html.fromHtml(data.html)

            Text(
                modifier = modifier,
                text = content.toAnnotatedString(),
                style = HtmlView().TextStyle(),
                color = if (isSystemInDarkTheme()) Color.White else Color.Black
            )
        }
    }
}