package kodz.org.core.component.webview

import android.graphics.Color
import android.os.Build
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import androidx.databinding.ViewDataBinding
import kodz.org.core.base.component.ComponentBaseContractor
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.databinding.ComponentWebviewBinding


class WebViewContractor() : ComponentBaseContractor() {
    override var itemClickHandler: ItemClickHandler? = null
    override var binding: ViewDataBinding? = null
    private var darkMode: Boolean = false

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        binding = viewDataBinding
        initComponent()
    }

    private fun initComponent() {
        (binding as? ComponentWebviewBinding)?.run {
            data?.let {
                it.content?.let { content ->
                    webView.run {

                        // Settings
                        isClickable = false
                        isScrollContainer = false
                        isVerticalScrollBarEnabled = false
                        isHapticFeedbackEnabled = false
                        clearCache(true)
                        settings.userAgentString = "Mozilla/4.0 (compatible; MSIE 5.01; Windows NT 5.0)";
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            settings.safeBrowsingEnabled = true
                        }
                        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
                        setBackgroundColor(Color.TRANSPARENT)
                        settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

                        // Load Data
                        loadData(
                            content,
                            "text/html; charset=utf-8",
                            "UTF-8"
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
        // Meta
        val meta = "<meta name='viewport' content='width=device-width'>"

        //CSS
        var css = "<style type='text/css'>" +
                "*{" +
                "font-family:Times New Roman!important; " +
                "line-height:1.5!important;" +
                "-webkit-user-select: none !important;" +
                "}" +
                "body{" +
                "font-family: Times New Roman!important;" +
                "font-size: 19px!important;" +
                "padding: 16px !important;" +
                "text-align: left !important;"

        if (darkMode) {
            css += "color: #adbac7 !important;" +
                    "background: #22272e !important;"
        } else {
            css += "color: #1c1c1c !important;" +
                    "background: #d4d6da !important;"
        }

        css += "}" +
                "img{" +
                "max-width:100% !important; " +
                "}" +
                "</style>"

        return "<!DOCTYPE html><html lang=\"tr\"><head>$meta</head><body>$css$this</body></html>"
    }

    private val customClient = object : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            view?.loadUrl("javascript:AndroidFunction.resize(document.body.scrollHeight)")
            super.onPageFinished(view, url)
        }
    }

    @JavascriptInterface
    fun resize(height: Float) {
        binding?.root?.context?.resources?.displayMetrics?.density?.let {
            val webViewHeight: Float = height * it
        }
        //webViewHeight is the actual height of the WebView in pixels as per device screen density
    }
}