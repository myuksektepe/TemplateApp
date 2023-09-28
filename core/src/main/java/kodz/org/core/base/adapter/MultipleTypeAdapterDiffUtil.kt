package kodz.org.core.base.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import kodz.org.core.base.component.ComponentBaseRow


/**
 * Created by Murat Yüksektepe on 9.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
class MultipleTypeAdapterDiffUtil : DiffUtil.ItemCallback<ComponentBaseRow>() {
    override fun areItemsTheSame(oldItem: ComponentBaseRow, newItem: ComponentBaseRow): Boolean {
        return oldItem == newItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: ComponentBaseRow, newItem: ComponentBaseRow): Boolean {
        return oldItem == newItem
    }
}