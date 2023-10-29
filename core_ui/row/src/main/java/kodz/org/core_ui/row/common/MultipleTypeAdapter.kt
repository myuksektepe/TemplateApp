package kodz.org.core_ui.row.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import kodz.org.core.base.row.BaseRow


/**
 * Created by Murat Yüksektepe on 9.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
open class MultipleTypeAdapter : ListAdapter<BaseRow, MultipleTypeAdapterViewHolder>(MultipleTypeAdapterDiffUtil()) {

    override fun getItemViewType(position: Int): Int {
        val rowModel = currentList[position] as BaseRow
        return rowModel.layout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultipleTypeAdapterViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), viewType, parent, false
        )
        return MultipleTypeAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MultipleTypeAdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}