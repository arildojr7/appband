package dev.arildo.appband.song.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import dev.arildo.appband.core.base.BaseViewModel
import dev.arildo.data.song.SongRepository
import dev.arildo.data.song.exception.FailureRequestException
import dev.arildo.data.song.exception.FailureRequestWithLocalDataException
import dev.arildo.data.song.model.Song
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.random.Random

class SongsViewModel(private val songRepository: SongRepository) : BaseViewModel() {

    private var _songs = MutableLiveData<List<Song>>()
    val songs: LiveData<List<Song>> = Transformations.map(_songs) { it }

    suspend fun getSongs() {
        try {
            songRepository.getSongs().collect { response ->

                if (response.isSuccessful) {
                    response.body()?.let { songList ->
                        _songs.postValue(songList)
                    }
                } else {
                    // error
                }
            }

        } catch (e: Exception) {
            when (e) {
                is FailureRequestException -> {
                    // error
                }
                is FailureRequestWithLocalDataException -> {
                    // show a toast to notify data is only local
                }
            }
        }
    }

    suspend fun filterSongs(filter: String?) {
        songRepository.getSongsFiltered("%$filter%").collect { response ->
            _songs.postValue(response.body())
        }
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
