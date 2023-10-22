package kodz.org.core_ui.row.search_box

import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import androidx.databinding.ViewDataBinding
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.handler.SearchHandler
import kodz.org.core.base.row.BaseRowContractor
import kodz.org.core_ui.row.databinding.RowSearchBoxBinding

class SearchBoxRowContractor : BaseRowContractor() {
    override var binding: ViewDataBinding? = null
    override var itemClickHandler: ItemClickHandler? = null
    var searchHandler: SearchHandler? = null

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        binding = viewDataBinding
        initComponent()
    }

    private fun initComponent() {
        (binding as? RowSearchBoxBinding)?.run {
            data?.let { data ->
                edtSearch.imeOptions = EditorInfo.IME_ACTION_SEARCH
                edtSearch.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                        // Do nothing
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        // Do nothing
                    }

                    override fun afterTextChanged(s: Editable?) {
                        val searchText = s.toString()
                        searchHandler?.searchedText(if (searchText.length >= 3) searchText else null)
                    }

                })
            }
        }
    }
}