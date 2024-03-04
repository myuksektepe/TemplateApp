package kodz.org.template

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.LifecycleOwner
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kodz.org.core.base.acitivity.BaseActivity
import kodz.org.core.base.viewmodel.SharedViewModel
import kodz.org.core.domain.enums.ButtonType
import kodz.org.core.domain.enums.CommonIcons
import kodz.org.core.domain.enums.ErrorType
import kodz.org.core.domain.extensions.gone
import kodz.org.core.domain.extensions.setSpamProtectedClickListener
import kodz.org.core.domain.extensions.visible
import kodz.org.core.domain.models.ButtonModel
import kodz.org.core.domain.models.ErrorModel
import kodz.org.core.domain.models.LoadingModel
import kodz.org.core_ui.component.button.MultipleTypeButton
import kodz.org.core_ui.component.text.ClassicTextView
import kodz.org.template.databinding.ActivityMainBinding
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor() :
    BaseActivity<MainViewModel, ActivityMainBinding>(R.layout.activity_main) {
    override val viewModel: MainViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by viewModels()
    override var viewLifeCycleOwner: LifecycleOwner = this
    override fun getBottomNavigationView(): BottomNavigationView = binding.bottomNavigation
    override fun getFragmentContainerView(): FragmentContainerView = binding.fragmentContainer
    override fun obverseViewModel() {}
    private var isAnyDialogVisible: Boolean = false
    private var dialog: Dialog? = null
    private val shownDialogs = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when (resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> {
                binding.toolBar.setNavigationIconTint(Color.WHITE)
            }

            Configuration.UI_MODE_NIGHT_NO -> {
                binding.toolBar.setNavigationIconTint(Color.BLACK)
            }

            Configuration.UI_MODE_NIGHT_UNDEFINED -> {}
        }
    }

    override fun showFullScreenLoading(loadingModel: LoadingModel?, view: View?) {
        binding.run {
            // Title
            txtLoadingTitle.text = loadingModel?.title ?: getString(kodz.org.core.R.string.loading)

            // Description
            txtLoadingDescription.text = loadingModel?.description
            txtLoadingDescription.movementMethod = ScrollingMovementMethod()
            if (loadingModel?.description.isNullOrEmpty()) txtLoadingDescription.gone()

            frmLoading.visible()
            frmShimmer.visible()
            allScreen.gone()
        }
        isAnyDialogVisible = true
    }

    override fun hideFullScreenLoading() {
        binding.run {
            frmLoading.gone()
            frmShimmer.gone()
            allScreen.visible()
        }
        isAnyDialogVisible = false
    }

    @SuppressLint("UseCompatLoadingForDrawables", "UseCompatTextViewDrawableApis")
    override fun showFullScreenError(errorModel: ErrorModel.ViewEntity) {
        hideFullScreenLoading()
        prepareDialog(errorModel)
    }

    override fun hideFullScreenError() {
        dialog?.dismiss()
        binding.run {
            frmShimmer.gone()
            allScreen.visible()
        }
        isAnyDialogVisible = false
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun setActionBarTitleAndIcon(title: String?, subTitle: String?, icon: CommonIcons?) {
        binding.run {
            if (!title.isNullOrEmpty()) {
                toolBar.title = title
                toolBar.subtitle = subTitle
                appBar.visible()
            }
            icon?.let { icon -> toolBar.navigationIcon = getDrawable(icon.resourceId) }
                ?: toolBar.setNavigationIcon(null)
            toolBar.setNavigationOnClickListener {
                if (!isAnyDialogVisible) {
                    if (icon == CommonIcons.GO_BACK) navigateUp()
                }
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables", "DiscouragedApi")
    private fun prepareDialog(errorModel: ErrorModel.ViewEntity) {
        dialog = Dialog(this)
        dialog?.run {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.bottom_sheet)
            setCancelable(errorModel.type == ErrorType.WARNING)

            // Close Button
            findViewById<ImageView>(R.id.imgClose).run {
                if (errorModel.type == ErrorType.WARNING) {
                    visible()
                    setSpamProtectedClickListener {
                        dismiss()
                    }
                } else gone()
            }

            errorModel.dialogBoxModel?.let { dialogBox ->
                // Lottie
                findViewById<LottieAnimationView>(R.id.lottieView).run {
                    dialogBox.lottie?.let { lottie ->
                        lottie.name?.resourceId?.let { rawFile ->
                            repeatCount =
                                if (lottie.loop == true) ValueAnimator.INFINITE else lottie.repeatCount
                                    ?: 0
                            if (lottie.autoPlay == true) animate()
                            setAnimation(rawFile)
                            visible()
                        }
                    } ?: kotlin.run { gone() }
                }

                // Title
                findViewById<ClassicTextView>(R.id.txtDialogTitle).run {
                    text = dialogBox.title?.asString(context) ?: getString(kodz.org.core.R.string.error)
                }

                // Description
                findViewById<ClassicTextView>(R.id.txtDialogDescription).run {
                    dialogBox.description?.let {
                        text = it.asString(context)
                        movementMethod = ScrollingMovementMethod()
                        visible()
                    } ?: kotlin.run {
                        gone()
                    }
                }

                // Primary & Secondary Button
                findViewById<LinearLayout>(R.id.buttons).run {
                    dialogBox.primaryButton?.let { addView(createButton(it.toViewEntity())) }
                    dialogBox.secondaryButton?.let { addView(createButton(it.toViewEntity(), true)) }
                }
            }

            dialog?.setOnDismissListener {
                isAnyDialogVisible = false
                binding.frmTranslucent.gone()
            }
        }

        if (errorModel.dialogBoxModel?.tag !in shownDialogs) {
            showDialog(errorModel.type)
            errorModel.dialogBoxModel?.tag?.let { shownDialogs.add(it) }
        } else {
            if (errorModel.dialogBoxModel?.showOnce == false || errorModel.dialogBoxModel?.showOnce == null) {
                showDialog(errorModel.type)
            }
        }
    }

    private fun showDialog(type: ErrorType?) {
        dialog?.run {
            if (type == ErrorType.BLOCKER) {
                binding.frmShimmer.visible()
                binding.allScreen.gone()
            }
            isAnyDialogVisible = true
            binding.frmTranslucent.visible()
            show()
            window?.run {
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                attributes.windowAnimations = R.style.DialogAnimation
                setGravity(Gravity.BOTTOM)
            }
        }
    }

    private fun createButton(button: ButtonModel.ViewEntity, addMarginTop: Boolean? = false) =
        MultipleTypeButton(this, null).apply {
            // Text
            setText(button.text?.asString(context) ?: getString(kodz.org.core.R.string.okay))

            // Text Color
            val textColor = if (button.textColor != null) {
                button.textColor!!
            } else resources.getColor(kodz.org.core.R.color.dayNightReverse)
            setTextColor(textColor)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) setIconColor(textColor)

            // TextWeight
            button.textWeightType?.let {
                setTextWeight(it)
            }

            // Icon
            button.icon?.let {
                getDrawable(it.resourceId)?.let { icon ->
                    setIcon(icon)
                }
            } ?: kotlin.run { setIcon(null) }

            // OnClick
            button.eventType?.let { eventTypeCode ->
                setSpamProtectedClickListener {
                    sharedViewModel.setClickEventCode(eventTypeCode)
                }
            }

            var layoutParams: LinearLayout.LayoutParams? = null
            when (button.type) {
                ButtonType.TEXT -> {
                    setBgColor(null)
                    setMatchParent(true)
                    button.showUnderline?.let { visible -> showUnderline(visible) }
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                }

                ButtonType.FILLED -> {
                    val backgroundColor = if (button.backgroundColor != null) {
                        button.backgroundColor!!
                    } else resources.getColor(kodz.org.core.R.color.green)
                    showUnderline(false)
                    setBgColor(backgroundColor)
                    setMatchParent(true)
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                }

                ButtonType.OUTLINED -> {
                    showUnderline(false)
                    setBgColor(null)
                    setMatchParent(true)
                    showOutline(textColor)
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                }

                else -> {}
            }

            if (addMarginTop == true) {
                layoutParams?.setMargins(0, 32, 0, 0)
            }

            layoutParams?.let { setLayoutParams(it) }
        }

    override fun onBackPressed() {
        if (isAnyDialogVisible) return
        super.onBackPressed()
    }
}