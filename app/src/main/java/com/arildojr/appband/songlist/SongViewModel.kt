package com.arildojr.appband.songlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.arildojr.appband.core.base.BaseViewModel
import com.arildojr.data.song.SongRepository
import com.arildojr.data.song.model.Song
import kotlinx.coroutines.launch
import kotlin.random.Random

class SongViewModel(private val songRepository: SongRepository) : BaseViewModel() {


    private var _songs = MutableLiveData<PagedList<Song>>()
    val songsa: LiveData<PagedList<Song>> = Transformations.map(_songs) { it }

//    init {
//
//        //populateDb()
//
//
//        val factory: DataSource.Factory<Int, Song> = songRepository.getSongs()
//        val pagedListBuilder: LivePagedListBuilder<Int, Song> = LivePagedListBuilder<Int, Song>(
//            factory,
//            50
//        )
//        _songs = pagedListBuilder.build()
//
//    }

    fun getSongsLiveData() = _songs

    private fun populateDb() {
//        launch {
//            val songs = songRepository.getSongs()
//            val gambi = songs + songs + songs + songs + songs + songs + songs
//
//            songRepository.insertAll(gambi.onEach {
//                it.id = Random.nextInt(0, Int.MAX_VALUE).toString()
//            })
//            delay(200)
//            songRepository.insertAll(gambi.onEach {
//                it.id = Random.nextInt(0, Int.MAX_VALUE).toString()
//            })
//            delay(200)
//            songRepository.insertAll(gambi.onEach {
//                it.id = Random.nextInt(0, Int.MAX_VALUE).toString()
//            })
//            delay(200)
//            songRepository.insertAll(gambi.onEach {
//                it.id = Random.nextInt(0, Int.MAX_VALUE).toString()
//            })
//            delay(200)
//            songRepository.insertAll(gambi.onEach {
//                it.id = Random.nextInt(0, Int.MAX_VALUE).toString()
//            })
//            delay(200)
//            songRepository.insertAll(gambi.onEach {
//                it.id = Random.nextInt(0, Int.MAX_VALUE).toString()
//            })
//            delay(200)
//            songRepository.insertAll(gambi.onEach {
//                it.id = Random.nextInt(0, Int.MAX_VALUE).toString()
//            })
//            delay(200)
//            songRepository.insertAll(gambi.onEach {
//                it.id = Random.nextInt(0, Int.MAX_VALUE).toString()
//            })
//            delay(200)
//            songRepository.insertAll(gambi.onEach {
//                it.id = Random.nextInt(0, Int.MAX_VALUE).toString()
//            })
//            delay(200)
//            songRepository.insertAll(gambi.onEach {
//                it.id = Random.nextInt(0, Int.MAX_VALUE).toString()
//            })
//            delay(200)
//
//        }
    }

    private var filterSongText = MutableLiveData<String>("")

    fun getSongs() = Transformations.switchMap<String, List<Song>>(filterSongText) { input ->
        if (input == null || input == "" || input == "%%") {
            //check if the current value is empty load all data else search
            songRepository.getSongs()
        } else {
            songRepository.getSongsFiltered(input)
        }

    }


    fun filterSongs(filter: String?) {
        filterSongText.postValue("%${filter}%")
    }

    fun addSong() {
        val name = Random.nextInt(100, 99999).toString()

        launch {
            songRepository.insertAll(
                listOf(
                    Song(
                        name,
                        name,
                        "Cantor",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTv109GjsK0_YyNx5G6eGanRReC6MVKAJ0OiY7IfrGggDmRm2m4"
                    )
                )
            )
        }
    }

}
