package com.arildojr.appband.songlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.arildojr.appband.core.base.BaseViewModel
import com.arildojr.data.song.SongRepository
import com.arildojr.data.song.model.Song
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class SongViewModel(private val songRepository: SongRepository) : BaseViewModel() {

    private var _songs : LiveData<PagedList<Song?>>

    init {

//        launch {
//            val songs = songRepository.getSongs()
//            val gambi = songs+songs+songs+songs+songs+songs+songs+songs+songs+songs+songs+songs+songs+songs+songs+songs+songs+songs+songs+songs+songs+songs+songs
//
//            songRepository.insertAll(gambi.onEach {
//                it.id = Random.nextInt(0, Int.MAX_VALUE).toString()
//            })
//            delay(100)
//
//        }
        val factory: DataSource.Factory<Int, Song> = songRepository.getAllPaged()
        val pagedListBuilder: LivePagedListBuilder<Int, Song>  = LivePagedListBuilder<Int, Song>(factory,
            50)
        _songs = pagedListBuilder.build()

    }

    fun getSongsLiveData() = _songs

}
