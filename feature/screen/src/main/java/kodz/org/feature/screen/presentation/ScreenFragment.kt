package kodz.org.feature.screen.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.net.toUri
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDeepLinkRequest
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kodz.org.core.base.fragment.BaseFragment
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.row.BaseRow
import kodz.org.core.base.viewmodel.SharedViewModel
import kodz.org.core.domain.consts.DASHBOARD_ENDPOINT
import kodz.org.core.domain.consts.ENDPOINT
import kodz.org.core.domain.enums.CommonIcons
import kodz.org.core.domain.enums.ErrorType
import kodz.org.core.domain.enums.EventTypeCode
import kodz.org.core.domain.extensions.gone
import kodz.org.core.domain.extensions.visible
import kodz.org.core.domain.models.ClickEventModel
import kodz.org.core.domain.models.ErrorModel
import kodz.org.core_ui.row.unrepeatable_item_rows.tabs_layout.tab_page.TabsLayoutPage
import kodz.org.core_ui.row.unrepeatable_item_rows.tabs_layout.tab_page.TabsLayoutPageAdapter
import kodz.org.feature.screen.R
import kodz.org.feature.screen.databinding.FragmentScreenBinding
import kodz.org.feature.screen.domain.adapter.ScreenAdapter
import kodz.org.feature.screen.domain.model.ScreenState
import kodz.org.feature.screen.domain.model.SettingsModel
import kodz.org.feature.screen.domain.model.TabModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ScreenFragment :
    BaseFragment<ScreenViewModel, FragmentScreenBinding>(R.layout.fragment_screen) {
    override val viewModel: ScreenViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    override val isBottomNavigationViewVisible = false
    private val rowAdapter = ScreenAdapter()
    private var endpoint: String? = null
    private var thisPageOpenedBefore: Boolean = false
    private val tabTitles = mutableListOf<Pair<String, Int?>>()

    override fun bindingViewModel(binding: FragmentScreenBinding) {
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun viewDidLoad(savedInstanceState: Bundle?) {
        setUI()

        arguments?.run {
            getString(ENDPOINT)?.let {
                endpoint = it
            } ?: kotlin.run {
                endpoint = DASHBOARD_ENDPOINT
            }
        }

        // AppLog("endpoint: $endpoint")
        // AppLog("thisPageOpenedBefore: $thisPageOpenedBefore")

        //viewModel.fetchAdapter(if (!thisPageOpenedBefore) endpoint else null)
        viewModel.fetchScreen(if (!thisPageOpenedBefore) endpoint else null)
        thisPageOpenedBefore = true
    }

    override fun observeViewModel() {
        viewModel.run {
            observeLiveData(searchedTextLiveData) {
                rowAdapter.filter.filter(it)
            }

            observeLiveData(clickEventModelLiveData) { clickEventModel ->
                clickEventModel?.let {
                    handleItemClickEvent(it)
                }
            }

            observeLiveData(sharedViewModel.eventTypeCodeLiveData) { eventTypeCode ->
                eventTypeCode?.let {
                    when (it) {
                        EventTypeCode.RETRY_LAST_ACTION -> {
                            hideFullScreenError()
                            //viewModel.fetchAdapter(endpoint)
                            viewModel.fetchScreen(endpoint)
                        }

                        EventTypeCode.CLOSE_THE_DIALOG -> {
                            hideFullScreenError()
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
                }
            }

            lifecycleScope.launch(Dispatchers.Main) {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.screenModelStateFlow.collectLatest { result ->
                        when (result) {
                            is ScreenState.Loading -> {
                                showFullScreenLoading(view = binding.root)
                            }

                            is ScreenState.Error -> {
                                showFullScreenError(result.errorModel)
                            }

                            is ScreenState.Success -> {
                                result.data?.run {
                                    // Settings
                                    prepareScreen(settings)

                                    // Rows
                                    rows?.let {
                                        showResultViaAdapter(rowAdapter, it)
                                    }

                                    // Tabs
                                    tabs?.let {
                                        showResultViaPageAdapter(
                                            TabsLayoutPageAdapter(fragmentManager = this@ScreenFragment.childFragmentManager, lifecycle),
                                            it
                                        )
                                    }

                                    // Error
                                    error?.let { showFullScreenError(it) }
                                }
                            }

                            null -> Unit
                        }
                    }
                }
            }

        }
    }

    private fun setUI() {
        binding.run {
            recyclerViewScreen.run {
                setItemViewCacheSize(rowAdapter.itemCount)
                rowAdapter.submitData(null)
                adapter = rowAdapter
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }

            /*
            swiperefresh.setOnRefreshListener {
                viewModel.fetchAdapter(endpoint)
                binding.searchBox.edtSearch.text = null
                swiperefresh.isRefreshing = false
            }
             */
        }
    }

    private fun showResultViaAdapter(adapter: ScreenAdapter, list: MutableList<BaseRow?>?) {
        adapter.submitData(list)
        binding.nestedScrollView.visible()
        binding.tabLayoutScreen.gone()
        binding.viewPagerScreen.gone()
        hideFullScreenError()
        hideFullScreenLoading()
    }

    private fun showResultViaPageAdapter(adapter: TabsLayoutPageAdapter, tabs: List<TabModel>) {
        binding.run {
            if (viewPagerScreen.adapter == null) viewPagerScreen.adapter = adapter
            tabLayoutScreen.removeAllTabs()
            tabs.forEach { tab ->
                tab.tabTitle?.let { tabText ->
                    tabTitles.add(tabText to tab.tabIcon?.resourceId)
                }

                tab.tabContent?.let { itemListJson ->
                    adapter.addFragment(TabsLayoutPage(
                        itemListJson = itemListJson,
                        itemClickHandler = object : ItemClickHandler {
                            override fun onItemClick(clickEventModel: ClickEventModel?) {
                                clickEventModel?.let {
                                    handleItemClickEvent(it)
                                }
                            }
                        }
                    ))
                }
            }

            TabLayoutMediator(tabLayoutScreen, viewPagerScreen) { tab, position ->
                tab.text = tabTitles[position].first
                tabTitles[position].second?.let { tab.setIcon(it) }
            }.attach()

            nestedScrollView.gone()
            tabLayoutScreen.visible()
            viewPagerScreen.visible()
            viewPagerScreen.isUserInputEnabled = false
            hideFullScreenError()
            hideFullScreenLoading()
        }
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
            .fromUri("android-app://${context?.packageName}/screenFragment?$ENDPOINT=${endpoint}".toUri())
            .build()
        navigateWithDeepLink(navDeepLinkRequest)
    }

    private fun handleItemClickEvent(eventModel: ClickEventModel) {
        when (eventModel.eventTypeCode) {
            EventTypeCode.OPEN_SCREEN -> {
                goToDeepLink(eventModel.endpoint)
            }

            EventTypeCode.GO_URL -> {
                eventModel.url?.let {
                    Intent(Intent.ACTION_VIEW).run {
                        data = Uri.parse(it)
                        startActivity(this)
                    }
                }
            }

            EventTypeCode.SHOW_ALERT_DIALOG -> {
                eventModel.dialogBoxModel?.let {
                    showFullScreenError(ErrorModel(ErrorType.WARNING, it))
                }
            }

            EventTypeCode.CLOSE_THE_DIALOG -> {
                hideFullScreenError()
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

    override fun onBackPressed(): Boolean {
        return true
    }
}