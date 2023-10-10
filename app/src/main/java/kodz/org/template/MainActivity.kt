package kodz.org.template

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kodz.org.core.base.acitivity.BaseActivity
import kodz.org.core.base.viewmodel.SharedViewModel
import kodz.org.core.common.AppLog
import kodz.org.core.common.CommonIcons
import kodz.org.core.extension.gone
import kodz.org.core.extension.setSpamProtectedClickListener
import kodz.org.core.extension.visible
import kodz.org.core.model.LoadingModel
import kodz.org.core.model.http.ErrorModel
import kodz.org.template.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(R.layout.activity_main) {
    override val viewModel: MainViewModel by viewModels()
    private lateinit var sharedViewModel: SharedViewModel
    override var viewLifeCycleOwner: LifecycleOwner = this
    override fun getBottomNavigationView(): BottomNavigationView = binding.bottomNavigation
    override fun getFragmentContainerView(): FragmentContainerView = binding.fragmentContainer

    private var view: View? = null
    private var isAnyDialogVisible: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel = ViewModelProvider(this)[SharedViewModel::class.java]
    }

    override fun obverseViewModel() {}

    override fun showFullScreenLoading(loadingModel: LoadingModel?, view: View?) {
        binding.apply {
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
        binding.apply {
            frmLoading.gone()
            frmShimmer.gone()
            allScreen.visible()
        }
        isAnyDialogVisible = false
    }

    @SuppressLint("UseCompatLoadingForDrawables", "UseCompatTextViewDrawableApis")
    override fun showFullScreenError(errorModel: ErrorModel, view: View?) {
        hideFullScreenLoading()
        binding.apply {
            // Title
            txtErrorTitle.text = errorModel.title ?: getString(kodz.org.core.R.string.error)

            // Description
            txtErrorDescription.apply {
                text = errorModel.description
                movementMethod = ScrollingMovementMethod()
            }
            txtLoadingDescription.movementMethod = ScrollingMovementMethod()

            // Primary Button
            errorModel.primaryButton?.let { button ->
                btnErrorPrimary.text = button.text ?: getString(kodz.org.core.R.string.okay)

                val textColor = if (button.textColor != null) Color.parseColor(button.textColor) else resources.getColor(R.color.white)
                btnErrorPrimary.setTextColor(textColor)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    btnErrorPrimary.compoundDrawableTintList = ColorStateList.valueOf(textColor)
                }

                val backgroundColor = if (button.backgroundColor != null) Color.parseColor(button.backgroundColor) else resources.getColor(kodz.org.core.R.color.success)
                btnErrorPrimary.setBackgroundColor(backgroundColor)

                val icon = button.icon?.let {
                    getDrawable(it.resourceId)
                }
                btnErrorPrimary.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null)

                button.eventType?.let { eventTypeCode ->
                    btnErrorPrimary.setSpamProtectedClickListener {
                        sharedViewModel.setClickEventCode(eventTypeCode)
                    }
                }

                btnErrorPrimary.visible()
            } ?: kotlin.run {
                btnErrorPrimary.gone()
            }

            // Secondary Button
            errorModel.secondaryButton?.let { button ->
                button.text?.let { text ->
                    btnErrorSecondary.text = text

                    val textColor = if (button.textColor != null) Color.parseColor(button.textColor) else resources.getColor(R.color.white)
                    btnErrorSecondary.setTextColor(textColor)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        btnErrorSecondary.compoundDrawableTintList = ColorStateList.valueOf(textColor)
                    }

                    val backgroundColor = if (button.backgroundColor != null) Color.parseColor(button.backgroundColor) else resources.getColor(kodz.org.core.R.color.scarlet)
                    btnErrorSecondary.setBackgroundColor(backgroundColor)

                    val icon = button.icon?.let {
                        getDrawable(it.resourceId)
                    }
                    btnErrorSecondary.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null)

                    button.eventType?.let { eventTypeCode ->
                        btnErrorSecondary.setSpamProtectedClickListener {
                            sharedViewModel.setClickEventCode(eventTypeCode)
                        }
                    }
                }
                btnErrorSecondary.visible()
            } ?: kotlin.run {
                btnErrorSecondary.gone()
            }

            // Layouts
            frmError.visible()
            frmShimmer.visible()
            allScreen.gone()
        }
        isAnyDialogVisible = true
    }

    override fun hideFullScreenError() {
        binding.apply {
            frmError.gone()
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

    private fun setClickable(view: View?, isClickable: Boolean) {
        lifecycleScope.launch(Dispatchers.Main) {
            if (view != null) {
                this@MainActivity.view = view
                view.isEnabled = isClickable
                AppLog("$isClickable - ${view.toString()}")
                if (view is ViewGroup) {
                    for (i in 0 until view.childCount) {
                        setClickable(view.getChildAt(i), isClickable)
                        AppLog("-- $isClickable - ${view.getChildAt(i).toString()}")
                    }
                }
            }
        }
    }
}