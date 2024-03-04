package kodz.org.core.base.fragment

import android.net.Uri
import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDirections
import kodz.org.core.base.acitivity.BaseActivity
import kodz.org.core.base.viewmodel.BaseViewModel
import kodz.org.core.domain.enums.CommonIcons
import kodz.org.core.domain.models.ErrorModel
import kodz.org.core.domain.models.LoadingModel
import kodz.org.core.domain.interfaces.OnBackPressed


/**
 * Created by Murat Yüksektepe on 9.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */

abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding>(private val view: Int) :
    Fragment(view), OnBackPressed {

    abstract val viewModel: BaseViewModel

    private var _binding: DB? = null
    val binding get() = _binding!!

    abstract fun observeViewModel()
    abstract fun viewDidLoad(savedInstanceState: Bundle?)
    abstract fun bindingViewModel(binding: DB)
    abstract val isBottomNavigationViewVisible: Boolean

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate<DB>(inflater, view, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        // this is require for Two-Way DataBinding
        bindingViewModel(binding)

        // StrictMode policy
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        setBottomNavigationViewVisibility(isBottomNavigationViewVisible)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDidLoad(savedInstanceState)
        observeViewModel()

        // Which fragment is visible?
        // val fragmentName = this::class.java.simpleName
        // AppLog("Created Fragment: $fragmentName\n-------------------------------")
    }

    fun showFullScreenLoading(loadingModel: LoadingModel? = null, view: View? = null) {
        (requireActivity() as BaseActivity<*, *>).showFullScreenLoading(loadingModel, view)
    }

    fun hideFullScreenLoading() {
        (requireActivity() as BaseActivity<*, *>).hideFullScreenLoading()
    }

    fun showFullScreenError(errorModel: ErrorModel.ViewEntity) {
        (requireActivity() as BaseActivity<*, *>).showFullScreenError(errorModel)
    }

    fun hideFullScreenError() {
        (requireActivity() as BaseActivity<*, *>).hideFullScreenError()
    }

    fun navigate(direction: NavDirections? = null, @IdRes actionId: Int? = null, uri: Uri? = null) {
        if (requireActivity() is BaseActivity<*, *>) {
            if (direction != null)
                (requireActivity() as BaseActivity<*, *>).navigate(direction = direction)

            if (actionId != null)
                (requireActivity() as BaseActivity<*, *>).navigate(actionId = actionId)

            if (uri != null)
                (requireActivity() as BaseActivity<*, *>).navigate(uri = uri)
        }
    }

    fun navigateWithDeepLink(deepLinkRequest: NavDeepLinkRequest) {
        if (requireActivity() is BaseActivity<*, *>) {
            (requireActivity() as BaseActivity<*, *>).navigateWithDeepLink(deepLinkRequest)
        }
    }

    fun navigateUp() {
        if (requireActivity() is BaseActivity<*, *>) {
            (requireActivity() as BaseActivity<*, *>).navigateUp()
        }
    }

    fun popBackStack() {
        if (requireActivity() is BaseActivity<*, *>) {
            (requireActivity() as BaseActivity<*, *>).popBackStack()
        }
    }

    private fun setBottomNavigationViewVisibility(isVisible: Boolean) {
        if (requireActivity() is BaseActivity<*, *>) {
            (requireActivity() as BaseActivity<*, *>).setBottomNavigationViewVisibility(isVisible)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun setActionBarTitleAndIcon(title: String, subTitle: String?, icon: CommonIcons?) {
        (requireActivity() as BaseActivity<*, *>).setActionBarTitleAndIcon(title, subTitle, icon)
    }

    fun <T> observeLiveData(liveData: LiveData<T>, observer: Observer<T>): Unit =
        liveData.observe(viewLifecycleOwner, observer)

    fun <T> observeLiveDataOnce(lifecycleOwner: LifecycleOwner, liveData: LiveData<T>, observer: Observer<T>) {
        liveData.observe(lifecycleOwner, object : Observer<T> {
            override fun onChanged(value: T) {
                observer.onChanged(value)
                liveData.removeObserver(this)
            }
        })
    }
}