package kodz.org.core.base.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import kodz.org.core.base.adapter.model.BaseRow


/**
 * Created by Murat Yüksektepe on 9.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
class MultipleTypeAdapterDiffUtil : DiffUtil.ItemCallback<BaseRow>() {
    override fun areItemsTheSame(oldItem: BaseRow, newItem: BaseRow): Boolean {
        return oldItem == newItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: BaseRow, newItem: BaseRow): Boolean {
        return oldItem == newItem
    }
}