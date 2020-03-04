package dev.arildo.appband.setlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.arildojr.data.song.SongRepository
import com.arildojr.data.song.exception.FailureRequestException
import com.arildojr.data.song.exception.FailureRequestWithLocalDataException
import com.arildojr.data.song.model.Song
import dev.arildo.appband.core.base.BaseViewModel
import kotlinx.coroutines.flow.collect

class SetListSetViewModel(private val songRepository: SongRepository) : BaseViewModel() {

    private var _songs = MutableLiveData<List<Song>>()
    val songs: LiveData<List<Song>> = Transformations.map(_songs) { it }

    private var _selectedSongs = MutableLiveData<List<Song>>()
    val selectedSongs: LiveData<List<Song>> = Transformations.map(_selectedSongs) { it }

    private var selectedDateTimestamp: Long = 0L

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

    fun addSelectedSong(song: Song) {
        val selectedSongs = mutableListOf<Song>().apply {
            addAll(_selectedSongs.value.orEmpty())
            add(song)
        }
        _selectedSongs.postValue(selectedSongs)

    }

    fun setDateTimestamp(timestamp: Long) {
        selectedDateTimestamp = timestamp
    }

    suspend fun filterSongs(filter: String?) {
        songRepository.getSongsFiltered("%$filter%").collect { response ->
            _songs.postValue(response.body())
        }
    }
}