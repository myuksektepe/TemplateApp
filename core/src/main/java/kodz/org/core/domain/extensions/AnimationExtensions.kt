package kodz.org.core.domain.extensions

import android.content.Context
import android.view.animation.AnimationUtils
import kodz.org.core.R


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 9.10.2023.
 */
fun Context.animSlideInDown() = AnimationUtils.loadAnimation(this, R.anim.slide_in_down)
fun Context.animSlideInLeft() = AnimationUtils.loadAnimation(this, R.anim.slide_right_to_left)
fun Context.animSlideInUp() = AnimationUtils.loadAnimation(this, R.anim.slide_in_up)
fun Context.animSlideOutDown() = AnimationUtils.loadAnimation(this, R.anim.slide_out_down)
fun Context.animFadeOut() = AnimationUtils.loadAnimation(this, R.anim.fade_out)
fun Context.animFadeIn() = AnimationUtils.loadAnimation(this, R.anim.fade_in)
fun Context.animBounce() = AnimationUtils.loadAnimation(this, R.anim.bounce)
fun Context.animShake() = AnimationUtils.loadAnimation(this, R.anim.shake)