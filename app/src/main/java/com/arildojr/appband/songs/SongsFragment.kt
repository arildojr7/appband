package com.arildojr.appband.songs

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log

import com.arildojr.appband.R
import com.arildojr.appband.core.base.BaseFragment
import com.arildojr.appband.databinding.SongsFragmentBinding
import com.arildojr.data.songs.model.Song

class SongsFragment : BaseFragment<SongsFragmentBinding>(R.layout.songs_fragment) {

    companion object {
        fun newInstance() = SongsFragment()
    }

    private lateinit var viewModel: SongsViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SongsViewModel::class.java)


        val adapter = SongsAdapter(emptyList()) {
            Log.e(">>>> ", it.title)
        }

        binding.rvSongs.adapter = adapter
        binding.rvSongs

        adapter.setData(
            listOf(
                Song(title = "Não seremos abalados", singer = "Michele Borges"),
                Song(title = "Em espírito, em verdade", singer = "Michele Borges"),
                Song(title = "Reina em mim", singer = "Michele Borges"),
                Song(title = "Coração igual ao teu", singer = "Michele Borges"),
                Song(title = "Recebi um novo coração", singer = "Michele Borges"),
                Song(title = "Há um lugar", singer = "Michele Borges"),
                Song(title = "Santo é o Senhor", singer = "Michele Borges"),
                Song(title = "Noites traiçoeiras", singer = "Michele Borges"),
                Song(title = "Meu Mestre", singer = "Michele Borges"),
                Song(title = "Ele é exaltado", singer = "Eli Soares")
            )
        )

    }

    fun scrollToTop() {}

}
