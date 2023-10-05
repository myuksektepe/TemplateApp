package kodz.org.feature.new_screen.presentation

import android.os.Bundle
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kodz.org.core.base.fragment.BaseFragment
import kodz.org.core.common.AppLog
import kodz.org.feature.new_screen.R
import kodz.org.feature.new_screen.databinding.NewScreenFragmentBinding

@AndroidEntryPoint
class NewScreenFragment : BaseFragment<NewScreenViewModel, NewScreenFragmentBinding>(R.layout.new_screen_fragment) {
    override val viewModel: NewScreenViewModel by viewModels()

    override fun viewDidLoad(savedInstanceState: Bundle?) {
        arguments?.run {
            val screenType = getString("screenType")
            val itemId = getString("itemId")
            val endpoint = getString("endpoint")

            AppLog("screenType: $screenType, itemId: $itemId, endpoint: $endpoint")

            endpoint?.let {
                viewModel.post(it)
            }
        }
    }

    override fun observeViewModel() {
    }

    override val isBottomNavigationViewVisible: Boolean = false

    override fun bindingViewModel(binding: NewScreenFragmentBinding) {
        binding.lifecycleOwner = this
    }

    override fun onBackPressed(): Boolean {
        return true
    }
}