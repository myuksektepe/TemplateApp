package kodz.org.core_ui.row.webview

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.graphics.Color
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.ViewDataBinding
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.BaseRowContractor
import kodz.org.core_ui.row.databinding.RowWebviewBinding

class WebViewRowContractor() : BaseRowContractor() {
    override var viewBinding: ViewDataBinding? = null
    lateinit var binding: RowWebviewBinding
    override var itemClickHandler: ItemClickHandler? = null

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        viewBinding = viewDataBinding
        binding = viewDataBinding as RowWebviewBinding
        initRow()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initRow() {
        binding.run {
            data?.let {
                it.content?.fullHtml()?.let { content ->
                    webView.run {

                        // Settings
                        isClickable = false
                        isScrollContainer = false
                        isVerticalScrollBarEnabled = false
                        isHapticFeedbackEnabled = false
                        setBackgroundColor(Color.TRANSPARENT)
                        /*
                        clearCache(true)
                        settings.userAgentString = "Mozilla/4.0 (compatible; MSIE 5.01; Windows NT 5.0)";
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            settings.safeBrowsingEnabled = true
                        }
                        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
                        settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                        settings.javaScriptEnabled = true
                        // settings.cacheMode = WebSettings.LOAD_NO_CACHE
                        // settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
                         */

                        // Load Data
                        loadDataWithBaseURL(
                            null,
                            content,
                            "text/html; charset=utf-8",
                            "UTF-8",
                            null
                        )

                        // Layout Params
                        layoutParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                        )
                    }
                }
            }
        }
    }

    private fun String.fullHtml(): String {
        var colorText = "color:black !important;"
        when (binding.root.context?.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> {
                colorText = "color:white !important;"
            }
        }

        // Meta
        val meta = "<meta name='viewport' content='width=device-width'>"

        //CSS
        val css = "<style type='text/css'>" +
                "*{" +
                "font-family:Times New Roman!important; " +
                "line-height:1.5!important;" +
                "-webkit-user-select: none !important;" +
                "}" +

                "body{" +
                "font-family: Times New Roman!important;" +
                "font-size: 19px!important;" +
                "padding: 16px !important;" +
                "text-align: left !important;" +
                colorText +
                "}" +

                "img{" +
                "max-width:100% !important; " +
                "}" +
                "</style>"

        return "<!DOCTYPE html><html lang=\"tr\"><head>$meta</head><body>$css$this</body></html>"
    }
}