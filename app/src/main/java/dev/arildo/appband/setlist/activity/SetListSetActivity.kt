package dev.arildo.appband.setlist.activity

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.arildojr.data.song.model.Song
import dev.arildo.appband.R
import dev.arildo.appband.core.base.BaseActivity
import dev.arildo.appband.databinding.ActivitySetListSetBinding
import dev.arildo.appband.setlist.adapter.SetListSongsAdapter
import dev.arildo.appband.setlist.customview.SelectSongsDialog
import dev.arildo.appband.setlist.viewmodel.SetListSetViewModel
import dev.arildo.appband.songlist.SongDetailActivity
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class SetListSetActivity : BaseActivity<ActivitySetListSetBinding>(R.layout.activity_set_list_set) {

    private val viewModel: SetListSetViewModel by inject()
    private val songs = mutableListOf<Song>()

    private val adapter2 = SetListSongsAdapter(emptyList()) {
        val bundle = Bundle()
        bundle.putParcelable("song", it)
        startActivity(Intent(this, SongDetailActivity::class.java).apply { putExtra("bundle", bundle) })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupRecycler()
        setupListeners()

        launch {
            viewModel.getSongs()
        }

    }

    private fun setupListeners() {
        binding.fabAddSongs.setOnClickListener {
            SelectSongsDialog.Companion.Builder(this).setListener(
                object : SelectSongsDialog.SelectSongsDialogListener {
                    override fun onSelectSong(song: Song, dialog: SelectSongsDialog?) {
                        viewModel.addSelectedSong(song)
                        dialog?.alertDialog?.dismiss()
                    }

                    override fun onDismissDialog(dialog: SelectSongsDialog?) {
                        dialog?.alertDialog?.dismiss()
                    }
                }
            ).setData(songs+songs+songs+songs+songs+songs+songs+songs).build()
        }
    }

    private fun setupRecycler() {
        binding.rvSongs.apply {
            adapter = adapter2
        }
    }

    override fun subscribeUi() {
        viewModel.selectedSongs.observe(this, Observer {
            adapter2.setData(it)
        })

        viewModel.songs.observe(this, Observer {
            songs.addAll(it)
        })
    }
}
