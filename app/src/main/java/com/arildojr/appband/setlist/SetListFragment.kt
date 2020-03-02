package com.arildojr.appband.setlist

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.arildojr.appband.R
import com.arildojr.appband.core.base.BaseFragment
import com.arildojr.appband.databinding.FragmentSetListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

class SetListFragment : BaseFragment<FragmentSetListBinding>(R.layout.fragment_set_list) {

    private val viewModel: SetListViewModel by inject()
    private val adapter2 = SetListAdapter(emptyList()) { setList, position ->
        val bundle = Bundle()
        bundle.putParcelable("setList", setList)
        bundle.putString("setListName", getString(R.string.set_list_number, position.toString()))
        startActivity(Intent(context, SetListDetailActivity::class.java).apply { putExtra("bundle", bundle) })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.rvSetLists.apply {
            adapter = adapter2
            layoutManager = GridLayoutManager(context, 2)

            viewTreeObserver.addOnScrollChangedListener {
                binding.llTopHeader.isSelected = this.canScrollVertically(-1)
            }
        }

        launch {
            withContext(Dispatchers.IO) {
                viewModel.getSetLists()

            }
        }

    }

    override fun subscribeUi() {
        launch {
            withContext(Dispatchers.Main) {
                viewModel.setList.observe(viewLifecycleOwner, Observer {
                    adapter2.setData(it)
                })
            }
        }
    }

    companion object {
        fun newInstance() =
            SetListFragment().apply {}
    }

    fun scrollToTop() {

    }
}
