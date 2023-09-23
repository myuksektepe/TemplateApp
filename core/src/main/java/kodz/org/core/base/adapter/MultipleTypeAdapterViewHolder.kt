package kodz.org.core.base.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import kodz.org.core.BR
import kodz.org.core.base.component.BaseRow


/**
 * Created by Murat Yüksektepe on 9.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
class MultipleTypeAdapterViewHolder(private var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(row: BaseRow) {
        binding.apply {
            setVariable(BR.data, row.dataModel)
            setVariable(BR.viewModel, row.viewModel)
            row.component.initBinding(this)
            executePendingBindings()
        }
    }
}