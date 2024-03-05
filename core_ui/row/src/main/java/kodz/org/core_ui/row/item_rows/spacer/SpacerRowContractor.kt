package kodz.org.core_ui.row.item_rows.spacer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.databinding.ViewDataBinding
import kodz.org.core.base.row.contractor.BaseItemRowContractor
import kodz.org.core.domain.interfaces.handler.ItemClickHandler
import kodz.org.core_ui.extension.sdp
import kodz.org.core_ui.row.databinding.RowComposeViewBinding
import kodz.org.core_ui.ui.TemplateAppTheme

class SpacerRowContractor(
    override val isInCarousel: Boolean? = null,
    override val isInList: Boolean? = null,
) : BaseItemRowContractor() {
    override var itemClickHandler: ItemClickHandler? = null
    override var viewBinding: ViewDataBinding? = null
    lateinit var binding: RowComposeViewBinding

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        viewBinding = viewDataBinding
        binding = (viewBinding as RowComposeViewBinding)
        initRow()
    }

    private fun initRow() {
        binding.run {
            (data as SpacerRowDataModel).let { data ->
                composeView.apply {
                    setContent {
                        TemplateAppTheme {
                            SpacerRow(
                                modifier = Modifier,
                                data = data,
                            )
                        }
                    }
                }
            }
        }
    }


    @Composable
    private fun SpacerRow(
        modifier: Modifier,
        data: SpacerRowDataModel,
    ) {

        Spacer(
            modifier = modifier.height(data.height.sdp),
        )

    }
}