package kodz.org.template.dashboard.presentation

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kodz.org.core.base.adapter.MultipleTypeAdapter
import kodz.org.core.base.component.BaseRow
import kodz.org.core.base.fragment.BaseFragment
import kodz.org.core.model.LoadingModel
import kodz.org.core.model.Resource
import kodz.org.template.R
import kodz.org.template.databinding.FragmentDashboardBinding

class DashboardFragment :
    BaseFragment<DashboardViewModel, FragmentDashboardBinding>(R.layout.fragment_dashboard) {
    override val viewModel: DashboardViewModel by viewModels()
    override val isBottomNavigationViewVisible = true
    private val rowAdapter = MultipleTypeAdapter()

    override fun bindingViewModel(binding: FragmentDashboardBinding) {
        binding.lifecycleOwner = this
    }

    override fun viewDidLoad(savedInstanceState: Bundle?) {
        setUI()
        viewModel.fetchAdapter()
    }

    override fun observeViewModel() {
        viewModel.rowList.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    showFullScreenLoading(LoadingModel())
                }

                is Resource.Error -> {
                    showFullScreenError(
                        errorModel = it.errorModel,
                        callback = {
                            hideFullScreenError()
                            viewModel.fetchAdapter()
                        }
                    )
                }

                is Resource.Success -> {
                    showResultViaAdapter(rowAdapter, it.data)
                }
            }
        }
    }

    private fun setUI() {
        binding.listDashboard.run {
            adapter = rowAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            // addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
            setHasFixedSize(true)
        }
    }

    private fun showResultViaAdapter(adapter: MultipleTypeAdapter, list: List<BaseRow>) {
        hideFullScreenLoading()
        adapter.submitList(list)
    }
}