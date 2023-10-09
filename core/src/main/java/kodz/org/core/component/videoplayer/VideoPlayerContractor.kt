package kodz.org.core.component.videoplayer

import android.media.MediaPlayer
import android.net.Uri
import android.widget.MediaController
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import kodz.org.core.base.component.ComponentBaseContractor
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.common.AppLog
import kodz.org.core.databinding.ComponentVideoPlayerBinding
import kodz.org.core.extension.animFadeOut
import java.io.FileNotFoundException


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 9.10.2023.
 */
class VideoPlayerContractor : ComponentBaseContractor() {
    override var itemClickHandler: ItemClickHandler? = null
    override var binding: ViewDataBinding? = null
    private var videoDuration: Int = 0
    private var videoCurrentTime: Int = 0
    private var progressNow: Int = 0
    private val jumpTime = 10000
    private var mediaPlayer: MediaPlayer? = null

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        binding = viewDataBinding
        initComponent()
    }

    private fun initComponent() {
        (binding as? ComponentVideoPlayerBinding)?.run {
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
                    val mediaController = MediaController(this.root.context)
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

                            if (data.isControllersVisible == true) setMediaController(mediaController)

                        } catch (e: Exception) {
                            AppLog(e.message.toString())
                        } catch (e: FileNotFoundException) {
                            AppLog(e.message.toString())
                        }
                    }
                }

            }
        }
    }


    private fun videoOnReady(mp: MediaPlayer) {
        // MediaPlayer
        mediaPlayer = mp
        mediaPlayer?.let {
            it.isLooping = false
            it.setVolume(1f, 1f)

            videoDuration = it.duration

            // SeekBar
            /*
            binding.progressBar.progress = 0
            binding.progressBar.max = 100
            setSeekbar()
            lifecycleScope.launch(Dispatchers.Main) {
                binding.txtTotalTime.text = videoDuration.toLong().getDurationText()
                binding.imgCover.context.animFadeOut()
                binding.btnPlayPause.setIconDrawable(resources.getDrawable(obi.dahi.core_ui.R.drawable.ic_pause))
            }.invokeOnCompletion { _ ->
                it.start()
            }
             */
        }
    }

    private fun videoOnFinish(mp: MediaPlayer) {
        mediaPlayer = mp
        /*
        lifecycleScope.launch(Dispatchers.Main) {
            mediaPlayer!!.seekTo(0)
            binding.btnPlayPause.setIconDrawable(resources.getDrawable(obi.dahi.core_ui.R.drawable.ic_play))
            binding.txtCurrentTime.text = "00:00"
            binding.progressBar.progress = 0
        }
         */
    }
}