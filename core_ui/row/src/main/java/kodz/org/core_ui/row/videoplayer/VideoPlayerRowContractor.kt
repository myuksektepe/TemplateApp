package kodz.org.core_ui.row.videoplayer

import android.media.MediaPlayer
import android.net.Uri
import android.widget.MediaController
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.BaseRowContractor
import kodz.org.core.common.AppLog
import kodz.org.core.extension.animFadeOut
import kodz.org.core.extension.getDurationText
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
    private var videoDuration: Int = 0
    private var videoCurrentTime: Int = 0
    private var progressNow: Int = 0
    private val jumpTime = 10000
    private var mediaPlayer: MediaPlayer? = null

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        binding = viewDataBinding as RowVideoPlayerBinding
        initComponent()
    }

    private fun initComponent() {
        (binding as? RowVideoPlayerBinding)?.run {
            data?.let { data ->

                // Thumbnail
                data.thumbnailUrl?.let {
                    val glideRequest = RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .transform(CenterCrop())
                        .override(170, 220)

                    Glide.with(this.root.context)
                        .load(it)
                        .apply(glideRequest)
                        .into(imgThumbnail)
                }

                // Video
                data.videoUrl?.let {
                    val uri = Uri.parse(it)
                    videoView.apply {
                        try {
                            setVideoURI(uri)
                            setOnPreparedListener { mp ->
                                videoOnReady(mp)
                                if (data.autoPlay == true) mp.start()
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

                /*
                if (data.isControllersVisible == true) {
                    // Video Buttons
                    btnPlayPause.setOnClickListener {
                        GlobalScope.launch(Dispatchers.Main) {
                            if (videoView.isPlaying) {
                                videoView.pause()
                                btnPlayPause.setIconDrawable(
                                    root.context.resources.getDrawable(kodz.org.core.R.drawable.ic_play)
                                )
                            } else {
                                videoView.start()
                                btnPlayPause.setIconDrawable(
                                    root.context.resources.getDrawable(kodz.org.core.R.drawable.ic_pause)
                                )
                            }
                        }
                    }

                    btnForward10.setOnClickListener {
                        GlobalScope.launch(Dispatchers.Main) {
                            videoView.seekTo(videoCurrentTime + jumpTime)
                        }
                    }

                    btnReplay10.setOnClickListener {
                        GlobalScope.launch(Dispatchers.Main) {
                            if (videoCurrentTime >= jumpTime) {
                                videoView.seekTo(videoCurrentTime - jumpTime)
                            } else {
                                videoView.seekTo(0)
                            }
                        }
                    }

                    // SeekBar
                    progressBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                            if (fromUser) {
                                GlobalScope.launch(Dispatchers.Main) {
                                    videoView.seekTo((progress * videoDuration) / 100)
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
                 */
            }
        }
    }

    private fun videoOnReady(mp: MediaPlayer) {
        val binding = (binding as RowVideoPlayerBinding)

        // MediaPlayer
        mediaPlayer = mp
        mediaPlayer?.let {
            it.isLooping = false
            it.setVolume(1f, 1f)

            videoDuration = it.duration

            // SeekBar
            binding.progressBar.progress = 0
            binding.progressBar.max = 100
            setSeekbar()
            GlobalScope.launch(Dispatchers.Main) {
                binding.txtTotalTime.text = videoDuration.toLong().getDurationText()
                binding.imgThumbnail.context.animFadeOut()
                binding.btnPlayPause.setIconDrawable(
                    binding.root.context.resources.getDrawable(kodz.org.core.R.drawable.ic_play)
                )
            }.invokeOnCompletion { _ ->
                // it.start()
            }
        }
    }

    private fun videoOnFinish(mp: MediaPlayer) {
        val binding = (binding as RowVideoPlayerBinding)
        mediaPlayer = mp
        GlobalScope.launch(Dispatchers.Main) {
            mediaPlayer!!.seekTo(0)
            binding.btnPlayPause.setIconDrawable(
                binding.root.context.resources.getDrawable(kodz.org.core.R.drawable.ic_play)
            )
            binding.txtCurrentTime.text = "00:00"
            binding.progressBar.progress = 0
        }
    }

    private fun setSeekbar() {
        val binding = (binding as RowVideoPlayerBinding)
        GlobalScope.launch(Dispatchers.IO) {
            do {
                videoCurrentTime = binding.videoView.currentPosition
                progressNow = ((videoCurrentTime * 100) / videoDuration)

                GlobalScope.launch(Dispatchers.Main) {
                    binding.txtCurrentTime.text = videoCurrentTime.toLong().getDurationText()
                    binding.progressBar.progress = progressNow
                }

                if (binding.progressBar.progress >= 100) break
                delay(1000)
            } while (progressNow <= 100)
        }
    }
}