package dev.arildo.appband.song.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.Observer
import dev.arildo.appband.R
import dev.arildo.appband.core.base.BaseFragment
import dev.arildo.appband.core.util.BUNDLE
import dev.arildo.appband.core.util.SONG
import dev.arildo.appband.databinding.SongsFragmentBinding
import dev.arildo.appband.song.activity.SongDetailActivity
import dev.arildo.appband.song.adapter.SongsAdapter
import dev.arildo.appband.song.viewmodel.SongsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

class SongsFragment : BaseFragment<SongsFragmentBinding>(R.layout.songs_fragment),
    SearchView.OnQueryTextListener {

    private val viewModel: SongsViewModel by inject()
    private val songsAdapter =
        SongsAdapter(emptyList()) {
            val bundle = Bundle()
            bundle.putParcelable(SONG, it)
            startActivity(Intent(context, SongDetailActivity::class.java).apply {
                putExtra(
                    BUNDLE,
                    bundle
                )
            })
        }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.svSongFinder.setOnQueryTextListener(this)

        binding.rvSongs.apply {
            adapter = songsAdapter

            viewTreeObserver.addOnScrollChangedListener {
                binding.llTopHeader.isSelected = this.canScrollVertically(-1)
            }
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
            withContext(Dispatchers.Main) {
                viewModel.songs.observe(viewLifecycleOwner, Observer {
                    songsAdapter.setData(it)
                    binding.pbLoaderSongs.visibility = View.GONE
                })
            }
        }
    }

    fun scrollToTop() {
        binding.rvSongs.smoothScrollToPosition(0)
    }

}
