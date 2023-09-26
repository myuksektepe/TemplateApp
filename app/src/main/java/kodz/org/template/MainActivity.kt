package kodz.org.template

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kodz.org.core.base.acitivity.BaseActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun obverseViewModel() {}

    override fun showFullScreenLoading(loadingModel: LoadingModel) {
        binding.apply {
            // Title
            txtLoadingTitle.text = loadingModel.title ?: getString(kodz.org.core.R.string.loading)

            // Description
            txtLoadingDescription.text = loadingModel.description
            txtLoadingDescription.movementMethod = ScrollingMovementMethod()
            if (loadingModel.description.isNullOrEmpty()) txtLoadingDescription.gone()

            frmLoading.visible()
        }
    }

    override fun hideFullScreenLoading() {
        binding.apply { frmLoading.gone() }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun showFullScreenError(errorModel: ErrorModel, callback: (() -> Unit?)?) {
        hideFullScreenLoading()
        binding.apply {
            // Title
            txtErrorTitle.text = errorModel.title ?: getString(kodz.org.core.R.string.error)

            // Description
            txtErrorDescription.text = errorModel.description
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
    }

    override fun hideFullScreenError() {
        binding.apply { frmError.gone() }
    }

}