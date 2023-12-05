package kodz.org.core.common

import android.content.Context
import android.util.AttributeSet
import android.widget.VideoView


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 9.10.2023.
 */
class MyVideoView : VideoView {
    private var mVideoWidth = ZERO
    private var mVideoHeight = ZERO

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {}
    constructor(context: Context?) : super(context) {}

    fun setVideoSize(width: Int, height: Int) {
        mVideoWidth = width
        mVideoHeight = height
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var width = getDefaultSize(mVideoWidth, widthMeasureSpec)
        var height = getDefaultSize(mVideoHeight, heightMeasureSpec)
        if (mVideoWidth > ZERO && mVideoHeight > ZERO) {
            if (mVideoWidth * height > width * mVideoHeight) {
                height = width * mVideoHeight / mVideoWidth
            } else if (mVideoWidth * height < width * mVideoHeight) {
                width = height * mVideoWidth / mVideoHeight
            } else {
                // Log.i("@@@", "aspect ratio is correct: " +
                // width+"/"+height+"="+
                // mVideoWidth+"/"+mVideoHeight);
            }
        }
        setMeasuredDimension(width, height)
    }
}