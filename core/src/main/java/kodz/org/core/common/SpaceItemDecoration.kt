package kodz.org.core.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kodz.org.core.common.consts.TWO
import kodz.org.core.common.consts.ZERO


class SpacesItemDecoration(space: Int) : RecyclerView.ItemDecoration() {
    private val halfSpace: Int

    init {
        halfSpace = space / TWO
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

class SpacesItemDecorationForCarousel(val space: Int) : RecyclerView.ItemDecoration() {
    private val halfSpace: Int = space / TWO

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        parent.setClipToPadding(false)

        view.setPadding(space, ZERO, space, ZERO)

        /*
        // First Item
        if (parent.getChildAdapterPosition(view) == ZERO) {
            view.setPadding(space, ZERO, halfSpace, ZERO)
        }

        // Last Item
        else if (parent.getChildAdapterPosition(view) == ((parent.adapter?.itemCount ?: ZERO) - ONE)) {
            parent.setPadding(halfSpace, halfSpace, space, halfSpace)
            outRect.top = halfSpace
            outRect.bottom = halfSpace
            outRect.left = halfSpace
            outRect.right = ZERO
        }

        else {
            if (parent.getPaddingLeft() != halfSpace) {
                parent.setPadding(halfSpace, halfSpace, halfSpace, halfSpace)
            }
            outRect.top = halfSpace
            outRect.bottom = halfSpace
            outRect.left = halfSpace
            outRect.right = halfSpace
        }

             */
    }
}