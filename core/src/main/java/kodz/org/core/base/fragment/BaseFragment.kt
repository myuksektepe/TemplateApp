package kodz.org.core.base.fragment

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
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
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import kodz.org.core.R
import kodz.org.core.base.viewmodel.BaseViewModel
import kodz.org.core.base.acitivity.BaseActivity
import kodz.org.core.model.ErrorModel
import kodz.org.core.model.ErrorType
import kodz.org.core.model.LoadingModel


/**
 * Created by Murat Yüksektepe on 9.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */

abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding>(private val view: Int) : Fragment(view) {

    abstract val viewModel: BaseViewModel

    private var _binding: DB? = null
    val binding get() = _binding!!

    abstract fun observeViewModel()
    abstract fun viewDidLoad(savedInstanceState: Bundle?)
    abstract fun bindingViewModel(binding: DB)
    abstract val isBottomNavigationViewVisible: Boolean

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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
        observeViewModel()
        viewDidLoad(savedInstanceState)
    }

    fun showFullScreenLoading(title: String? = null, description: String? = null) {
        (requireActivity() as BaseActivity<*, *>).showFullScreenLoading(
            LoadingModel(
                isShow = true,
                title = title ?: requireContext().getString(R.string.pleaseWait),
                description = description
            )
        )
    }

    fun hideFullScreenLoading() {
        (requireActivity() as BaseActivity<*, *>).hideFullScreenLoading()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun showFullScreenError(
        type: ErrorType,
        title: String?,
        description: String?,
        buttonText: String? = null,
        buttonIcon: Drawable? = null,
        buttonClickListener: View.OnClickListener
    ) {
        (requireActivity() as BaseActivity<*, *>).showFullScreenError(
            ErrorModel(
                isShow = true,
                title = title,
                description = description,
                buttonText = if (type == ErrorType.BLOCKER) null else buttonText ?: getString(R.string.tryAgain),
                buttonIcon = if (type == ErrorType.BLOCKER) null else buttonIcon ?: requireContext().getDrawable(R.drawable.ic_refresh),
            ),
            buttonClickListener = buttonClickListener
        )
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

    fun navigateUp() {
        if (requireActivity() is BaseActivity<*, *>) {
            (requireActivity() as BaseActivity<*, *>).navigateUp()
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

    fun <T> observeLiveData(liveData: LiveData<T>, observer: Observer<T>): Unit = liveData.observe(viewLifecycleOwner, observer)
}