package kodz.org.template.dashboard.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kodz.org.core.base.acitivity.BaseActivity
import kodz.org.core.base.adapter.MultipleTypeAdapter
import kodz.org.core.model.ErrorModel
import kodz.org.core.model.LoadingModel
import kodz.org.template.R
import kodz.org.template.databinding.ActivityDashboardBinding

@AndroidEntryPoint
class DashboardActivity : BaseActivity<DashboardViewModel, ActivityDashboardBinding>(R.layout.activity_dashboard) {
    override val viewModel: DashboardViewModel by viewModels()
    override var viewLifeCycleOwner: LifecycleOwner = this
    private val rowAdapter = MultipleTypeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUI()
        viewModel.fetchAdapter()
    }

    override fun obverseViewModel() {
        viewModel.rowList.observe(viewLifeCycleOwner) {
            rowAdapter.submitList(it)
        }
    }

    private fun setUI() {
        binding.listDashboard.run {
            adapter = rowAdapter
            layoutManager = LinearLayoutManager(this@DashboardActivity, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(this@DashboardActivity, LinearLayoutManager.VERTICAL))
            setHasFixedSize(true)
        }
    }

    override fun getBottomNavigationView(): BottomNavigationView? = binding.bottomNavigation

    override fun showFullScreenLoading(loadingModel: LoadingModel) {
        Log.i("applog", loadingModel.title.toString())
    }

    override fun hideFullScreenLoading() {
        Log.i("applog", "...")
    }

    override fun showFullScreenError(errorModel: ErrorModel, buttonClickListener: View.OnClickListener?) {
    }

    override fun hideFullScreenError() {
    }

}