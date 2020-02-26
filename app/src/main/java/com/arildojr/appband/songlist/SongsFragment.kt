package com.arildojr.appband.songlist

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.lifecycle.Observer
import com.arildojr.appband.R
import com.arildojr.appband.core.base.BaseFragment
import com.arildojr.appband.databinding.SongsFragmentBinding
import com.arildojr.appband.songdetail.SongDetailActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

class SongsFragment : BaseFragment<SongsFragmentBinding>(R.layout.songs_fragment),
    SearchView.OnQueryTextListener {

    private val viewModel: SongViewModel by inject()
    private val adapter2 = SongsAdapter(emptyList()) {
        val bundle = Bundle()
        bundle.putParcelable("song", it)
        startActivity(Intent(context, SongDetailActivity::class.java).apply { putExtra("bundle", bundle) })
    }

    companion object {
        fun newInstance() = SongsFragment()
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        binding.svSongFinder.setOnQueryTextListener(this)

        binding.rvSongs.apply {
            adapter = adapter2

            viewTreeObserver.addOnScrollChangedListener {
                binding.llTopHeader.isSelected = this.canScrollVertically(-1)
            }
        }


        binding.fabAdd.setOnClickListener {
            viewModel.addSong()
        }

        launch {
            withContext(Dispatchers.IO) {
                viewModel.getSongs()

            }
        }
    }

    override fun onQueryTextSubmit(p0: String?) = true

    override fun onQueryTextChange(p0: String?): Boolean {
        launch {
            withContext(Dispatchers.IO) {
                viewModel.filterSongs(p0)
            }
        }

        return true
    }

    override fun subscribeUi() {
        launch {
            delay(128)
            withContext(Dispatchers.Main) {
                viewModel.songs.observe(viewLifecycleOwner, Observer {
                    adapter2.setData(it)
                })
            }
        }
    }

    fun scrollToTop() {
        binding.rvSongs.smoothScrollToPosition(0)
    }

}
