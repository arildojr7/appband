package com.arildojr.appband.songlist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.lifecycle.Observer
import com.arildojr.appband.R
import com.arildojr.appband.core.base.BaseFragment
import com.arildojr.appband.databinding.SongsFragmentBinding
import com.arildojr.appband.songdetail.SongDetailActivity
import org.koin.android.ext.android.inject

class SongsFragment : BaseFragment<SongsFragmentBinding>(R.layout.songs_fragment),
    SearchView.OnQueryTextListener {

    private val viewModel: SongViewModel by inject()
    private val adapter = SongsAdapter(emptyList()) {
        startActivity(Intent(context, SongDetailActivity::class.java))
    }

    companion object {


        fun newInstance() = SongsFragment()
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        binding.svSongFinder.setOnQueryTextListener(this)

        binding.rvSongs.adapter = adapter
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        Log.e(">> ", p0!!)
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        Log.e(">> ", p0!!)

        return true
    }

    override fun subscribeUi() {
        viewModel.getSongsLiveData().observe(viewLifecycleOwner, Observer {
            adapter.setData(it.filterNotNull())
        })
    }

    fun scrollToTop() {}

}
