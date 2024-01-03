package kodz.org.core_ui.component.list

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import kodz.org.core_ui.component.R

@SuppressLint("UseCompatLoadingForDrawables", "RestrictedApi")
class RecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : RecyclerView(context, attrs) {

    init {
        inflate(context, R.layout.component_recycler_view, null)
        setHasFixedSize(true)
    }

}