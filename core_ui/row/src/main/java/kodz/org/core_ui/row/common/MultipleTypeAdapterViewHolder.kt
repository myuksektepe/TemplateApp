package kodz.org.core_ui.row.common

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import kodz.org.core.base.row.BaseRow
import kodz.org.core_ui.row.BR


/**
 * Created by Murat Yüksektepe on 9.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
class MultipleTypeAdapterViewHolder(private var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(row: BaseRow) {
        binding.run {
            setVariable(BR.data, row.dataModel)
            row.contractor.initBinding(this)
            executePendingBindings()
        }
    }
}