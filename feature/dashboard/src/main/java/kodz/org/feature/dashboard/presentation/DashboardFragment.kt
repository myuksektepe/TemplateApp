package kodz.org.feature.dashboard.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kodz.org.core.base.component.ComponentBaseRow
import kodz.org.core.base.fragment.BaseFragment
import kodz.org.core.common.AppLog
import kodz.org.core.common.CommonIcons
import kodz.org.core.extension.gone
import kodz.org.core.extension.visible
import kodz.org.core.model.LoadingModel
import kodz.org.core.model.Resource
import kodz.org.core.model.screen.EventTypeCode
import kodz.org.core.model.screen.SettingsModel
import kodz.org.feature.dashboard.R
import kodz.org.feature.dashboard.databinding.FragmentDashboardBinding
import kodz.org.feature.dashboard.domain.adapter.DashboardAdapter

@AndroidEntryPoint
class DashboardFragment :
    BaseFragment<DashboardViewModel, FragmentDashboardBinding>(R.layout.fragment_dashboard) {
    override val viewModel: DashboardViewModel by viewModels()
    override val isBottomNavigationViewVisible = false
    private val rowAdapter = DashboardAdapter()
    private var endPoint: String? = "dashboard.json"
    override fun bindingViewModel(binding: FragmentDashboardBinding) {
        binding.lifecycleOwner = this
    }

    override fun viewDidLoad(savedInstanceState: Bundle?) {
        setUI()
        arguments?.run {
            endPoint = getString("endpoint")
            AppLog("endpoint: $endPoint")
            endPoint?.let { viewModel.fetchAdapter(it) }
        } ?: run {
            viewModel.fetchAdapter("dashboard.json")
        }
    }

    override fun observeViewModel() {
        viewModel.run {
            observeLiveData(screenSettingsLiveData) {
                prepareScreen(it)
            }

            observeLiveData(searchedTextLiveData) {
                rowAdapter.filter.filter(it)
            }

            observeLiveData(rowListLiveData) {
                when (it) {
                    is Resource.Loading -> {
                        showFullScreenLoading(LoadingModel())
                    }

                    is Resource.Error -> {
                        showFullScreenError(
                            errorModel = it.errorModel,
                            callback = {
                                hideFullScreenError()
                                viewModel.fetchAdapter(endPoint ?: "dashboard.json")
                            }
                        )
                    }

                    is Resource.Success -> {
                        showResultViaAdapter(rowAdapter, it.data.toMutableList())
                    }
                }
            }

            observeLiveData(clickEventModelLiveData) { clickEventModel ->
                clickEventModel?.let {
                    when (it.eventTypeCode) {
                        EventTypeCode.OPEN_SCREEN -> {
                            val navDeepLinkRequest = NavDeepLinkRequest.Builder.fromUri(
                                "android-app://kodz.org.template/mainScreenFragment?endpoint=${it.endpoint}".toUri()
                            ).build()
                            navigateWithDeepLink(navDeepLinkRequest)
                        }

                        EventTypeCode.CLOSE_THE_SCREEN -> {
                            navigateUp()
                        }

                        EventTypeCode.CLOSE_THE_APP -> {
                            activity?.finish()
                        }

                        else -> {
                            // Do nothing
                        }
                    }
                }
            }
        }
    }

    private fun setUI() {
        binding.run {
            listDashboard.run {
                adapter = rowAdapter
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                // setHasFixedSize(true)
            }

            swiperefresh.setOnRefreshListener {
                viewModel.fetchAdapter(endPoint ?: "dashboard.json")
                binding.searchBox.edtSearch.text = null
                swiperefresh.isRefreshing = false
            }
        }
    }

    private fun showResultViaAdapter(adapter: DashboardAdapter, list: MutableList<ComponentBaseRow?>?) {
        hideFullScreenLoading()
        adapter.submitData(list)
    }

    private fun prepareScreen(screenSettingsModel: SettingsModel?) {
        screenSettingsModel?.let {
            // Title & Icon
            it.title?.let { title ->
                setActionBarTitleAndIcon(
                    title = title,
                    subTitle = it.subTitle,
                    icon = if (it.isBackIconVisible == true) CommonIcons.GO_BACK else it.customIcon
                )
            }

            // SearchBox
            if (it.isSearchBoxVisible == true) {
                binding.searchBox.edtSearch.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                        // Do nothing
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        // Do nothing
                    }

                    override fun afterTextChanged(s: Editable?) {
                        val searchText = s.toString()
                        rowAdapter.filter.filter(if (searchText.length >= 3) searchText else null)
                    }

                })
                binding.searchBox.root.visible()
            } else binding.searchBox.root.gone()
        }
    }
}