package kodz.org.template

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
import androidx.activity.viewModels
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kodz.org.core.base.acitivity.BaseActivity
import kodz.org.core.base.viewmodel.SharedViewModel
import kodz.org.core.common.CommonIcons
import kodz.org.core.common.EMPTY
import kodz.org.core.extension.gone
import kodz.org.core.extension.setSpamProtectedClickListener
import kodz.org.core.extension.toColor
import kodz.org.core.extension.visible
import kodz.org.core.model.ButtonType
import kodz.org.core.model.ErrorModel
import kodz.org.core.model.ErrorType
import kodz.org.core.model.LoadingModel
import kodz.org.core_ui.component.button.RoundedButton
import kodz.org.core_ui.component.button.TextButton
import kodz.org.core_ui.component.text.ClassicTextView
import kodz.org.template.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(R.layout.activity_main) {
    override val viewModel: MainViewModel by viewModels()
    private lateinit var sharedViewModel: SharedViewModel
    override var viewLifeCycleOwner: LifecycleOwner = this
    override fun getBottomNavigationView(): BottomNavigationView = binding.bottomNavigation
    override fun getFragmentContainerView(): FragmentContainerView = binding.fragmentContainer
    override fun obverseViewModel() {}
    private var view: View? = null
    private var isAnyDialogVisible: Boolean = false
    private var dialog: Dialog? = null

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
        binding.frmTranslucent.visible()
        if (errorModel.type == ErrorType.BLOCKER) {
            binding.frmShimmer.visible()
            binding.allScreen.gone()
        }
        isAnyDialogVisible = true
        showDialog(errorModel)
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
            icon?.let { icon -> toolBar.navigationIcon = getDrawable(icon.resourceId) } ?: toolBar.setNavigationIcon(null)
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

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun showDialog(errorModel: ErrorModel) {
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

            // Title
            findViewById<ClassicTextView>(R.id.txtDialogTitle).run {
                text = errorModel.title ?: getString(kodz.org.core.R.string.error)
            }

            // Description
            findViewById<ClassicTextView>(R.id.txtDialogDescription).run {
                text = errorModel.description ?: EMPTY
                movementMethod = ScrollingMovementMethod()
            }

            // Primary Button
            findViewById<RoundedButton>(R.id.btnDialogPrimary).run {
                errorModel.primaryButton?.let { button ->
                    // Text
                    setText(button.text ?: getString(kodz.org.core.R.string.okay))

                    // Text Color
                    val textColor = if (button.textColor.toColor() != null) {
                        button.textColor.toColor()!!
                    } else resources.getColor(R.color.white)
                    setTextColor(textColor)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) setIconColor(textColor)

                    // Background Color
                    val backgroundColor = if (button.backgroundColor.toColor() != null) {
                        button.backgroundColor.toColor()!!
                    } else resources.getColor(kodz.org.core.R.color.green)
                    setBgColor(backgroundColor)

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
                            dismiss()
                        }
                    }

                    visible()
                } ?: kotlin.run { gone() }
            }

            // Secondary Button
            findViewById<TextButton>(R.id.btnDialogSecondaryText).gone()
            findViewById<RoundedButton>(R.id.btnDialogSecondaryRounded).gone()

            if (errorModel.secondaryButton?.type == ButtonType.ROUNDED) {
                findViewById<RoundedButton>(R.id.btnDialogSecondaryRounded).run {
                    errorModel.secondaryButton?.let { button ->
                        // Text
                        setText(button.text ?: getString(kodz.org.core.R.string.okay))

                        // Text Color
                        val textColor = if (button.textColor.toColor() != null) {
                            button.textColor.toColor()!!
                        } else resources.getColor(R.color.white)
                        setTextColor(textColor)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) setIconColor(textColor)

                        // Background Color
                        val backgroundColor = if (button.backgroundColor.toColor() != null) {
                            button.backgroundColor.toColor()!!
                        } else resources.getColor(kodz.org.core.R.color.red)
                        setBgColor(backgroundColor)

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
                                dismiss()
                            }
                        }

                        visible()
                    } ?: kotlin.run { gone() }
                }
            } else if (errorModel.secondaryButton?.type == ButtonType.TEXT) {
                findViewById<TextButton>(R.id.btnDialogSecondaryText).run {
                    errorModel.secondaryButton?.let { button ->
                        // Text
                        setText(button.text ?: getString(kodz.org.core.R.string.okay))

                        // Text Color
                        button.textColor.toColor()?.let { textColor ->
                            setTextColor(textColor)
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) setIconColor(textColor)
                            setUnderlineColor(textColor)
                        }

                        // Icon
                        button.icon?.let {
                            getDrawable(it.resourceId)?.let { icon ->
                                setIcon(icon)
                            }
                        } ?: kotlin.run { setIcon(null) }

                        // Underline
                        button.showUnderline?.let {
                            underline(it)
                        }

                        // OnClick
                        button.eventType?.let { eventTypeCode ->
                            setSpamProtectedClickListener {
                                sharedViewModel.setClickEventCode(eventTypeCode)
                                dismiss()
                            }
                        }

                        visible()
                    } ?: kotlin.run { gone() }
                }
            }

            show()

            window?.run {
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                attributes.windowAnimations = R.style.DialogAnimation
                setGravity(Gravity.BOTTOM)
            }

            dialog?.setOnDismissListener {
                isAnyDialogVisible = false
                binding.frmTranslucent.gone()
            }

        }
    }
}