package kodz.org.core.domain.models

import android.content.Context
import androidx.annotation.StringRes

sealed class TextModel {
    data class DynamicText(val value: String?) : TextModel()
    class StaticText(
        @StringRes val id: Int,
        vararg val args: Any?
    ) : TextModel()


    fun asString(context: Context): String? {
        return when (this) {
            is DynamicText -> value
            is StaticText -> context.getString(id, *args)
        }
    }
}