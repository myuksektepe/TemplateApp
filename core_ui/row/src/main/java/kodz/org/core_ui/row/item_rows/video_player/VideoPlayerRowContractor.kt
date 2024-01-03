package kodz.org.core_ui.row.item_rows.video_player

import android.annotation.SuppressLint
import android.graphics.SurfaceTexture
import android.media.MediaPlayer
import android.view.Surface
import android.view.TextureView
import android.widget.SeekBar
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import kodz.org.core.GlideApp
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.contractor.BaseItemRowContractor
import kodz.org.core.common.AppLog
import kodz.org.core.common.consts.HUNDRED
import kodz.org.core.common.consts.ZERO
import kodz.org.core.extension.animFadeOut
import kodz.org.core.extension.getDurationText
import kodz.org.core.extension.prepareForGroup
import kodz.org.core.extension.visible
import kodz.org.core_ui.component.button.CircleImageButton
import kodz.org.core_ui.row.databinding.RowVideoPlayerBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.FileNotFoundException


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 9.10.2023.
 */
class VideoPlayerRowContractor(
    override val isInCarousel: Boolean? = null,
    override val isInList: Boolean? = null
) : BaseItemRowContractor() {
    override var viewBinding: ViewDataBinding? = null
    lateinit var binding: RowVideoPlayerBinding
    override var itemClickHandler: ItemClickHandler? = null
    private var videoDuration: Int = ZERO
    private var videoCurrentTime: Int = ZERO
    private var progressNow: Int = ZERO
    private var mediaPlayer: MediaPlayer? = null

    companion object {
        const val START_DURATION_STRING = "00:00"
        const val THUMBNAIL_WIDTH = 170
        const val THUMBNAIL_HEIGHT = 220
        const val VOLUME_MAX = 1f
        const val DELAY_ONE_SEC: Long = 1000
        const val JUMP_TIME_MILLI_SEC = 10000
    }

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        viewBinding = viewDataBinding
        binding = viewDataBinding as RowVideoPlayerBinding
        initRow()
    }

    private fun initRow() {
        binding.run {
            data?.let { data ->

                // Paddings
                rowVideoPlayerRoot.prepareForGroup(isInList, isInCarousel)

                // Thumbnail
                data.thumbnailUrl?.let {
                    val glideRequest = RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .transform(CenterCrop())
                        .override(THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT)

                    GlideApp.with(this.root.context)
                        .load(it)
                        .apply(glideRequest)
                        .into(imgThumbnail)
                }

                // Video
                data.videoUrl?.let { url ->
                    textureView.run {
                        try {
                            this.surfaceTextureListener =
                                object : TextureView.SurfaceTextureListener {
                                    override fun onSurfaceTextureAvailable(
                                        surface: SurfaceTexture,
                                        width: Int,
                                        height: Int
                                    ) {
                                        mediaPlayer = MediaPlayer().apply {
                                            setDataSource(url)
                                            setSurface(Surface(surface))
                                            prepare()

                                            setOnPreparedListener { mp ->
                                                onVideoReady(mp)
                                                if (data.autoPlay == true) {
                                                    mp.start()
                                                    btnPlayPause.setPause()
                                                }
                                            }
                                            setOnCompletionListener { mp ->
                                                onVideoFinish(mp)
                                            }
                                            setOnInfoListener { _, what, _ ->
                                                if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                                                    imgThumbnail.startAnimation(this@run.context.animFadeOut())
                                                }
                                                true
                                            }
                                            setOnErrorListener { _, what, extra ->
                                                AppLog("Video setOnErrorListener what: $what")
                                                AppLog("Video setOnErrorListener extra: $extra")
                                                true
                                            }

                                            // setOnBufferingUpdateListener { mp, percent -> }
                                        }
                                    }

                                    override fun onSurfaceTextureSizeChanged(
                                        surface: SurfaceTexture,
                                        width: Int,
                                        height: Int
                                    ) {
                                    }

                                    override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean {
                                        mediaPlayer?.stop()
                                        mediaPlayer = null
                                        return false
                                    }

                                    override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {
                                    }
                                }

                        } catch (e: Exception) {
                            AppLog(e.message.toString())
                        } catch (e: FileNotFoundException) {
                            AppLog(e.message.toString())
                        }
                    }
                }

                if (data.isControllersVisible == true) {
                    controllers.visible()

                    // Video Buttons
                    btnPlayPause.setOnClickListener {
                        GlobalScope.launch(Dispatchers.Main) {
                            if (mediaPlayer?.isPlaying == true) {
                                mediaPlayer?.pause()
                                btnPlayPause.setPlay()
                            } else {
                                mediaPlayer?.start()
                                btnPlayPause.setPause()
                            }
                        }
                    }

                    btnForward10.setOnClickListener {
                        GlobalScope.launch(Dispatchers.Main) {
                            mediaPlayer?.seekTo(videoCurrentTime + JUMP_TIME_MILLI_SEC)
                        }
                    }

                    btnReplay10.setOnClickListener {
                        GlobalScope.launch(Dispatchers.Main) {
                            if (videoCurrentTime >= JUMP_TIME_MILLI_SEC) {
                                mediaPlayer?.seekTo(videoCurrentTime - JUMP_TIME_MILLI_SEC)
                            } else {
                                mediaPlayer?.seekTo(ZERO)
                            }
                        }
                    }

                    // SeekBar
                    progressBar.setOnSeekBarChangeListener(object :
                        SeekBar.OnSeekBarChangeListener {
                        override fun onProgressChanged(
                            seekBar: SeekBar?,
                            progress: Int,
                            fromUser: Boolean
                        ) {
                            if (fromUser) {
                                GlobalScope.launch(Dispatchers.Main) {
                                    mediaPlayer?.seekTo((progress * videoDuration) / HUNDRED)
                                }
                            }
                        }

                        override fun onStartTrackingTouch(p0: SeekBar?) {
                            // do nothing
                        }

                        override fun onStopTrackingTouch(p0: SeekBar?) {
                            // do nothing
                        }

                    })
                }
            }
        }
    }

    private fun onVideoReady(mp: MediaPlayer) {
        val binding = (binding as RowVideoPlayerBinding)

        // MediaPlayer
        mp.let {
            it.isLooping = false
            it.setVolume(VOLUME_MAX, VOLUME_MAX)
            videoDuration = it.duration

            // SeekBar
            binding.progressBar.progress = ZERO
            binding.progressBar.max = HUNDRED
            setSeekbar()
            GlobalScope.launch(Dispatchers.Main) {
                binding.txtTotalTime.text = videoDuration.toLong().getDurationText()
                binding.imgThumbnail.context.animFadeOut()
            }.invokeOnCompletion { _ ->
                // it.start()
            }
        }
    }

    private fun onVideoFinish(mp: MediaPlayer) {
        GlobalScope.launch(Dispatchers.Main) {
            mp.seekTo(ZERO)
            binding.run {
                btnPlayPause.setReplay()
                txtCurrentTime.text = START_DURATION_STRING
                progressBar.progress = ZERO
            }
        }
    }

    private fun setSeekbar() {
        GlobalScope.launch(Dispatchers.IO) {
            do {
                mediaPlayer?.currentPosition?.let { videoCurrentTime = it }
                progressNow = ((videoCurrentTime * HUNDRED) / videoDuration)

                GlobalScope.launch(Dispatchers.Main) {
                    binding.txtCurrentTime.text = videoCurrentTime.toLong().getDurationText()
                    binding.progressBar.progress = progressNow
                }

                if (binding.progressBar.progress >= HUNDRED) break
                delay(DELAY_ONE_SEC)
            } while (progressNow <= HUNDRED)
        }
    }

    // Extensions
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun CircleImageButton.setPlay() {
        this.setIconDrawable(
            this.context.resources.getDrawable(kodz.org.core.R.drawable.ic_play)
        )
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun CircleImageButton.setPause() {
        this.setIconDrawable(
            this.context.resources.getDrawable(kodz.org.core.R.drawable.ic_pause)
        )
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun CircleImageButton.setReplay() {
        this.setIconDrawable(
            this.context.resources.getDrawable(kodz.org.core.R.drawable.ic_refresh)
        )
    }

}