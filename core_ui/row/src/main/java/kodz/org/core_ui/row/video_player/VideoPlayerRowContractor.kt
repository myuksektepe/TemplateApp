package kodz.org.core_ui.row.video_player

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.net.Uri
import android.widget.MediaController
import android.widget.SeekBar
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.BaseRowContractor
import kodz.org.core.common.AppLog
import kodz.org.core.common.HUNDRED
import kodz.org.core.common.ZERO
import kodz.org.core.extension.animFadeOut
import kodz.org.core.extension.getDurationText
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
class VideoPlayerRowContractor : BaseRowContractor() {
    override var itemClickHandler: ItemClickHandler? = null
    override var binding: ViewDataBinding? = null
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
        binding = viewDataBinding as RowVideoPlayerBinding
        initRow()
    }

    private fun initRow() {
        (binding as? RowVideoPlayerBinding)?.run {
            data?.let { data ->

                // Thumbnail
                data.thumbnailUrl?.let {
                    val glideRequest = RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .transform(CenterCrop())
                        .override(THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT)

                    Glide.with(this.root.context)
                        .load(it)
                        .apply(glideRequest)
                        .into(imgThumbnail)
                }

                // Video
                data.videoUrl?.let {
                    val uri = Uri.parse(it)
                    videoView.run {
                        try {
                            setVideoURI(uri)
                            setOnPreparedListener { mp ->
                                videoOnReady(mp)
                                if (data.autoPlay == true) {
                                    mp.start()
                                    btnPlayPause.setPause()
                                }
                            }
                            setOnCompletionListener { mp ->
                                videoOnFinish(mp)
                            }
                            setOnErrorListener { mp, what, extra ->
                                AppLog("Video setOnErrorListener what: $what")
                                AppLog("Video setOnErrorListener extra: $extra")
                                true
                            }
                            setOnInfoListener { _, what, _ ->
                                if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                                    imgThumbnail.startAnimation(this.rootView.context.animFadeOut())
                                }
                                true
                            }

                            if (data.isControllersVisible == true) {
                                val mediaController = MediaController(this.context)
                                setMediaController(mediaController)
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
                            if (videoView.isPlaying) {
                                videoView.pause()
                                btnPlayPause.setPlay()
                            } else {
                                videoView.start()
                                btnPlayPause.setPause()
                            }
                        }
                    }

                    btnForward10.setOnClickListener {
                        GlobalScope.launch(Dispatchers.Main) {
                            videoView.seekTo(videoCurrentTime + JUMP_TIME_MILLI_SEC)
                        }
                    }

                    btnReplay10.setOnClickListener {
                        GlobalScope.launch(Dispatchers.Main) {
                            if (videoCurrentTime >= JUMP_TIME_MILLI_SEC) {
                                videoView.seekTo(videoCurrentTime - JUMP_TIME_MILLI_SEC)
                            } else {
                                videoView.seekTo(ZERO)
                            }
                        }
                    }

                    // SeekBar
                    progressBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                            if (fromUser) {
                                GlobalScope.launch(Dispatchers.Main) {
                                    videoView.seekTo((progress * videoDuration) / HUNDRED)
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

    private fun videoOnReady(mp: MediaPlayer) {
        val binding = (binding as RowVideoPlayerBinding)

        // MediaPlayer
        mediaPlayer = mp
        mediaPlayer?.let {
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

    private fun videoOnFinish(mp: MediaPlayer) {
        val binding = (binding as RowVideoPlayerBinding)
        mediaPlayer = mp
        GlobalScope.launch(Dispatchers.Main) {
            mediaPlayer!!.seekTo(ZERO)
            binding.btnPlayPause.setReplay()
            binding.txtCurrentTime.text = START_DURATION_STRING
            binding.progressBar.progress = ZERO
        }
    }

    private fun setSeekbar() {
        val binding = (binding as RowVideoPlayerBinding)
        GlobalScope.launch(Dispatchers.IO) {
            do {
                videoCurrentTime = binding.videoView.currentPosition
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