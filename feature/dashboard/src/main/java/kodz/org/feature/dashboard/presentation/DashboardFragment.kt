package kodz.org.feature.dashboard.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDeepLinkRequest
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kodz.org.core.base.component.ComponentBaseRow
import kodz.org.core.base.fragment.BaseFragment
import kodz.org.core.base.viewmodel.SharedViewModel
import kodz.org.core.common.AppLog
import kodz.org.core.common.CommonIcons
import kodz.org.core.common.DASHBOARD_ENDPOINT
import kodz.org.core.extension.gone
import kodz.org.core.extension.visible
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
    private lateinit var sharedViewModel: SharedViewModel
    override val isBottomNavigationViewVisible = false
    private val rowAdapter = DashboardAdapter()
    private var endpoint: String? = null
    private var thisPageOpenedBefore: Boolean = false
    override fun bindingViewModel(binding: FragmentDashboardBinding) {
        binding.lifecycleOwner = this
    }

    override fun viewDidLoad(savedInstanceState: Bundle?) {
        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        setUI()

        arguments?.run {
            getString("endpoint")?.let {
                endpoint = it
            } ?: kotlin.run {
                endpoint = DASHBOARD_ENDPOINT
            }
        }

        AppLog("endpoint: $endpoint")
        AppLog("thisPageOpenedBefore: $thisPageOpenedBefore")

        viewModel.fetchAdapter(if (!thisPageOpenedBefore) endpoint else null)
        thisPageOpenedBefore = true
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
                        showFullScreenLoading(view = binding.root)
                    }

                    is Resource.Error -> {
                        showFullScreenError(
                            errorModel = it.errorModel,
                            view = binding.root
                        )
                    }

                    is Resource.Success -> {
                        if (it.data.isNotEmpty()) showResultViaAdapter(rowAdapter, it.data.toMutableList())
                    }
                }
            }

            observeLiveData(itemClickEventModelLiveData) { clickEventModel ->
                clickEventModel?.let {
                    when (it.eventTypeCode) {
                        EventTypeCode.OPEN_SCREEN -> {
                            goToDeepLink(it.endpoint)
                        }

                        EventTypeCode.CLOSE_THE_SCREEN -> {
                            hideFullScreenError()
                            navigateUp()
                        }

                        EventTypeCode.CLOSE_THE_APP -> {
                            hideFullScreenError()
                            activity?.finish()
                        }

                        else -> {
                            // Do nothing
                        }
                    }
                    viewModel.clearLiveData()
                }
            }

            observeLiveData(sharedViewModel.getClickEventCode()) { eventTypeCode ->
                eventTypeCode?.let {
                    when (it) {
                        EventTypeCode.RETRY_LAST_ACTION -> {
                            viewModel.fetchAdapter(endpoint)
                        }

                        EventTypeCode.CLOSE_THE_SCREEN -> {
                            hideFullScreenError()
                            navigateUp()
                        }

                        EventTypeCode.CLOSE_THE_APP -> {
                            hideFullScreenError()
                            activity?.finish()
                        }

                        else -> {
                            // Do nothing
                        }
                    }
                    sharedViewModel.setClickEventCode(null)
                }
            }
        }
    }

    private fun setUI() {
        binding.run {
            listDashboard.run {
                rowAdapter.submitData(null)
                adapter = rowAdapter
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                // setHasFixedSize(true)
            }

            swiperefresh.setOnRefreshListener {
                viewModel.fetchAdapter(endpoint)
                binding.searchBox.edtSearch.text = null
                swiperefresh.isRefreshing = false
            }
        }
    }

    private fun showResultViaAdapter(adapter: DashboardAdapter, list: MutableList<ComponentBaseRow?>?) {
        hideFullScreenLoading()
        hideFullScreenError()
        adapter.submitData(list)
    }

    private fun prepareScreen(screenSettingsModel: SettingsModel?) {
        screenSettingsModel?.let {
            // Title & Icon
            it.title?.let { title ->
                setActionBarTitleAndIcon(
                    title = title,
                    subTitle = it.subTitle,
                    icon = if (it.isBackIconVisible == true) CommonIcons.GO_BACK else CommonIcons.from(it.customIcon.toString())
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

    private fun goToDeepLink(endpoint: String?) {
        val navDeepLinkRequest = NavDeepLinkRequest.Builder
            .fromUri("android-app://kodz.org.template/dashboardFragment?endpoint=${endpoint}".toUri())
            .build()
        navigateWithDeepLink(navDeepLinkRequest)
    }

    override fun onBackPressed(): Boolean {
        return true
    }
}