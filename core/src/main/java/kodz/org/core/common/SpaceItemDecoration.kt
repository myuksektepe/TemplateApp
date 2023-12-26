package kodz.org.core.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class SpacesItemDecoration(space: Int) : RecyclerView.ItemDecoration() {
    private val halfSpace: Int

    init {
        halfSpace = space / 2
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (parent.getPaddingLeft() != halfSpace) {
            parent.setPadding(halfSpace, halfSpace, halfSpace, halfSpace)
            parent.setClipToPadding(false)
        }
        outRect.top = halfSpace
        outRect.bottom = halfSpace
        outRect.left = halfSpace
        outRect.right = halfSpace
    }
}