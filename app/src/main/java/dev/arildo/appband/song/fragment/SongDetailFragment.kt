package dev.arildo.appband.song.fragment

import android.os.Bundle
import dev.arildo.appband.R
import dev.arildo.appband.core.base.BaseFragment
import dev.arildo.appband.core.util.SONG
import dev.arildo.appband.databinding.FragmentSongDetailBinding
import dev.arildo.data.song.model.Song

class SongDetailFragment : BaseFragment<FragmentSongDetailBinding>(R.layout.fragment_song_detail) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            binding.song = it.getParcelable(SONG)
        }
    }

    companion object {
        fun newInstance(song: Song?) =
            SongDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(SONG, song)
                }
            }
    }

}
