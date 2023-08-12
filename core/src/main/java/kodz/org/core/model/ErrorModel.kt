package kodz.org.core.model

import android.graphics.drawable.Drawable


/**
 * Created by Murat Yüksektepe on 9.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
data class ErrorModel(
    var isShow: Boolean = false,
    val title: String?,
    val description: String?,
    val buttonText: String?,
    val buttonIcon: Drawable?,
    val isCloseButtonVisible: Boolean = true
)