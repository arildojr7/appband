package dev.arildo.appband.setlist.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import dev.arildo.appband.R
import dev.arildo.appband.core.base.BaseFragment
import dev.arildo.appband.core.util.BUNDLE
import dev.arildo.appband.core.util.SET_LIST
import dev.arildo.appband.databinding.FragmentSetListBinding
import dev.arildo.appband.setlist.activity.SetListDetailActivity
import dev.arildo.appband.setlist.activity.SetListSetActivity
import dev.arildo.appband.setlist.adapter.SetListAdapter
import dev.arildo.appband.setlist.viewmodel.SetListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

class SetListFragment : BaseFragment<FragmentSetListBinding>(R.layout.fragment_set_list) {

    private val viewModel: SetListViewModel by inject()
    private val setListAdapter =
        SetListAdapter(emptyList()) { setList ->
            val bundle = Bundle()
            bundle.putParcelable(SET_LIST, setList)
            startActivity(
                Intent(context, SetListDetailActivity::class.java).apply {
                    putExtra(BUNDLE, bundle)
                })
        }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecycler()
        setupCreateSetListButton()
    }

    override fun onResume() {
        super.onResume()

        launch {
            withContext(Dispatchers.IO) {
                viewModel.getSetLists()
            }
        }
    }

    private fun setupRecycler() {
        binding.rvSetLists.apply {
            adapter = setListAdapter
            layoutManager = GridLayoutManager(context, 2)

            viewTreeObserver.addOnScrollChangedListener {
                binding.llTopHeader.isSelected = this.canScrollVertically(-1)
            }
        }
    }

    private fun setupCreateSetListButton() {
        binding.fabAddSetList.setOnClickListener {
            startActivity(Intent(context, SetListSetActivity::class.java))
        }
    }

    override fun subscribeUi() {
        launch {
            withContext(Dispatchers.Main) {
                viewModel.setList.observe(viewLifecycleOwner, Observer {
                    setListAdapter.setData(it)
                    binding.pbLoaderSetLists.visibility = View.GONE
                })
            }
        }
    }

    fun scrollToTop() {

    }
}
