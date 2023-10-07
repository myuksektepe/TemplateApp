package kodz.org.template

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kodz.org.core.base.acitivity.BaseActivity
import kodz.org.core.common.AppLog
import kodz.org.core.common.CommonIcons
import kodz.org.core.extension.gone
import kodz.org.core.extension.visible
import kodz.org.core.model.ErrorModel
import kodz.org.core.model.LoadingModel
import kodz.org.template.databinding.ActivityMainBinding


@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(R.layout.activity_main) {
    override val viewModel: MainViewModel by viewModels()
    override var viewLifeCycleOwner: LifecycleOwner = this
    override fun getBottomNavigationView(): BottomNavigationView = binding.bottomNavigation
    override fun getFragmentContainerView(): FragmentContainerView? = binding.fragmentContainer

    private var view: View? = null
    private var isAnyDialogVisible: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun obverseViewModel() {}

    override fun showFullScreenLoading(loadingModel: LoadingModel?, view: View?) {
        binding.apply {
            setClickable(view, false)

            // Title
            txtLoadingTitle.text = loadingModel?.title ?: getString(kodz.org.core.R.string.loading)

            // Description
            txtLoadingDescription.text = loadingModel?.description
            txtLoadingDescription.movementMethod = ScrollingMovementMethod()
            if (loadingModel?.description.isNullOrEmpty()) txtLoadingDescription.gone()

            frmLoading.visible()
        }
        isAnyDialogVisible = true
    }

    override fun hideFullScreenLoading() {
        binding.apply {
            setClickable(view, true)
            frmLoading.gone()
        }
        isAnyDialogVisible = false
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun showFullScreenError(errorModel: ErrorModel, callback: (() -> Unit?)?, view: View?) {
        hideFullScreenLoading()
        binding.apply {
            setClickable(view, false)

            // Title
            txtErrorTitle.text = errorModel.title ?: getString(kodz.org.core.R.string.error)

            // Description
            txtErrorDescription.apply {
                text = errorModel.description
                movementMethod = ScrollingMovementMethod()
            }
            txtLoadingDescription.movementMethod = ScrollingMovementMethod()

            // Primary Button
            btnErrorPrimary.text = errorModel.buttonText ?: getString(kodz.org.core.R.string.okay)
            btnErrorPrimary.setOnClickListener { callback?.invoke() }
            errorModel.buttonIcon?.let {
                val icon = getDrawable(it)
                btnErrorPrimary.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null)
            }

            // Cancel Button
            if (errorModel.isCancelButtonVisible) btnErrorCancel.visible() else btnErrorCancel.gone()
            btnErrorCancel.setOnClickListener { frmError.gone() }

            frmError.visible()
        }
        isAnyDialogVisible = true
    }

    override fun hideFullScreenError() {
        binding.apply {
            setClickable(view, true)
            frmError.gone()
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
        if (view != null) {
            this.view = view
            // view.isClickable = isClickable
            // view.isFocusable = isClickable
            // view.isScrollContainer = isClickable
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