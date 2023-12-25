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
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kodz.org.core.base.acitivity.BaseActivity
import kodz.org.core.base.viewmodel.SharedViewModel
import kodz.org.core.common.CommonIcons
import kodz.org.core.common.CommonSettings
import kodz.org.core.extension.gone
import kodz.org.core.extension.setSpamProtectedClickListener
import kodz.org.core.extension.toColor
import kodz.org.core.extension.visible
import kodz.org.core.model.ButtonModel
import kodz.org.core.model.ButtonType
import kodz.org.core.model.ErrorModel
import kodz.org.core.model.ErrorType
import kodz.org.core.model.LoadingModel
import kodz.org.core_ui.component.button.MultipleButton
import kodz.org.core_ui.component.text.ClassicTextView
import kodz.org.template.databinding.ActivityMainBinding
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor() :
    BaseActivity<MainViewModel, ActivityMainBinding>(R.layout.activity_main) {
    override val viewModel: MainViewModel by viewModels()
    private lateinit var sharedViewModel: SharedViewModel
    override var viewLifeCycleOwner: LifecycleOwner = this
    override fun getBottomNavigationView(): BottomNavigationView = binding.bottomNavigation
    override fun getFragmentContainerView(): FragmentContainerView = binding.fragmentContainer
    override fun obverseViewModel() {}
    private var isAnyDialogVisible: Boolean = false
    private var dialog: Dialog? = null
    private val shownDialogs = mutableListOf<String>()

    @Inject
    lateinit var commonSettings: CommonSettings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel = ViewModelProvider(this)[SharedViewModel::class.java]

        when (resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> {
                binding.toolBar.setNavigationIconTint(Color.WHITE)
            }

            Configuration.UI_MODE_NIGHT_NO -> {
                binding.toolBar.setNavigationIconTint(Color.BLACK)
            }

            Configuration.UI_MODE_NIGHT_UNDEFINED -> {}
        }

        // Device Width & Height
        commonSettings.deviceWidthInDp = resources.displayMetrics.run { widthPixels / density }
        commonSettings.deviceHeightInDp = resources.displayMetrics.run { heightPixels / density }
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
    override fun showFullScreenError(errorModel: ErrorModel) {
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

    override fun onBackPressed() {
        if (isAnyDialogVisible) return
        super.onBackPressed()
    }

    @SuppressLint("UseCompatLoadingForDrawables", "DiscouragedApi")
    private fun prepareDialog(errorModel: ErrorModel) {
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

            errorModel.dialogBox?.let { dialogBox ->
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
                    text = dialogBox.title ?: getString(kodz.org.core.R.string.error)
                }

                // Description
                findViewById<ClassicTextView>(R.id.txtDialogDescription).run {
                    dialogBox.description?.let {
                        text = it
                        movementMethod = ScrollingMovementMethod()
                        visible()
                    } ?: kotlin.run {
                        gone()
                    }
                }

                // Primary & Secondary Button
                findViewById<LinearLayout>(R.id.buttons).run {
                    dialogBox.primaryButton?.let { addView(createButton(it)) }
                    dialogBox.secondaryButton?.let { addView(createButton(it, true)) }
                }
            }

            dialog?.setOnDismissListener {
                isAnyDialogVisible = false
                binding.frmTranslucent.gone()
            }
        }

        if (errorModel.dialogBox?.tag !in shownDialogs) {
            showDialog(errorModel.type)
            errorModel.dialogBox?.tag?.let { shownDialogs.add(it) }
        } else {
            if (errorModel.dialogBox?.showOnce == false || errorModel.dialogBox?.showOnce == null) {
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

    private fun createButton(button: ButtonModel, addMarginTop: Boolean? = false) =
        MultipleButton(this, null).apply {
            // Text
            setText(button.text ?: getString(kodz.org.core.R.string.okay))

            // Text Color
            val textColor = if (button.textColor.toColor() != null) {
                button.textColor.toColor()!!
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
                    dialog?.dismiss()
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
                    val backgroundColor = if (button.backgroundColor.toColor() != null) {
                        button.backgroundColor.toColor()!!
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
}