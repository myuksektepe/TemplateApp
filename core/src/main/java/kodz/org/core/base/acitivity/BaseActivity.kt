package kodz.org.core.base.acitivity

import android.net.Uri
import android.os.Bundle
import android.os.StrictMode
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kodz.org.core.base.viewmodel.BaseViewModel
import kodz.org.core.extension.safeNavigate
import kodz.org.core.extension.setIsVisible
import kodz.org.core.model.ErrorModel
import kodz.org.core.model.LoadingModel

/**
 * Created by Murat Yüksektepe on 9.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
abstract class BaseActivity<VM : BaseViewModel, VDB : ViewDataBinding>(private val view: Int) :
    AppCompatActivity() {

    abstract val viewModel: VM
    abstract var viewLifeCycleOwner: LifecycleOwner
    abstract fun obverseViewModel()

    private var _binding: VDB? = null
    val binding get() = _binding!!

    private var _networkConnection: MutableLiveData<Boolean>? = MutableLiveData()
    val networkConnection get() = _networkConnection!!

    abstract fun getBottomNavigationView(): BottomNavigationView?

    protected var navHost: NavHostFragment? = null
    open fun getNavHostFragment(): NavHostFragment? = null

    private fun initBinding() {
        viewLifeCycleOwner = this
        _binding?.lifecycleOwner = viewLifeCycleOwner
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        // Hide statusbar
        // requestWindowFeature(Window.FEATURE_NO_TITLE)
        // this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        // Hide soft-key bar
        // this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        super.onCreate(savedInstanceState)

        initBinding()
        obverseViewModel()

        // Data view binding
        _binding = DataBindingUtil.inflate(layoutInflater, view, null, false)
        setContentView(_binding!!.root)

        // Navigation host
        navHost = getNavHostFragment()
        navHost?.let {
            getBottomNavigationView()?.setupWithNavController(it.navController)
        }

        // StrictMode policy
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    abstract fun showFullScreenLoading(loadingModel: LoadingModel)
    abstract fun hideFullScreenLoading()

    abstract fun showFullScreenError(errorModel: ErrorModel, callback: (() -> Unit?)? = null)
    abstract fun hideFullScreenError()

    fun navigate(
        direction: NavDirections? = null,
        @IdRes actionId: Int? = null,
        uri: Uri? = null
    ) {
        if (direction != null)
            navHost?.navController?.safeNavigate(direction)
        if (actionId != null)
            navHost?.navController?.safeNavigate(actionId)
        if (uri != null) {
            val request = NavDeepLinkRequest.Builder.fromUri(uri).build()
            navHost?.navController?.navigate(request)
        }
    }

    fun navigateUp() = navHost?.navController?.navigateUp()

    protected fun setStartDestinationOfNavGraph(navGraphId: Int, destinationId: Int) {
        val inflater = navHost?.navController?.navInflater
        val graph = inflater?.inflate(navGraphId)
        graph?.setStartDestination(destinationId)
        graph?.let {
            navHost?.navController?.graph = graph
        }
    }

    fun setBottomNavigationViewVisibility(isVisible: Boolean) {
        getBottomNavigationView()?.setIsVisible(isVisible)
    }
}