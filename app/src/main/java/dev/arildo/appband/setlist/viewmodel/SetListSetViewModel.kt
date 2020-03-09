package dev.arildo.appband.setlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import dev.arildo.data.musician.MusicianRepository
import dev.arildo.data.musician.model.Musician
import dev.arildo.data.setlist.SetListRepository
import dev.arildo.data.setlist.dto.SetListDTO
import dev.arildo.data.song.SongRepository
import dev.arildo.data.song.exception.FailureRequestException
import dev.arildo.data.song.exception.FailureRequestWithLocalDataException
import dev.arildo.data.song.model.Song
import dev.arildo.appband.core.base.BaseViewModel
import kotlinx.coroutines.flow.collect

class SetListSetViewModel(
    private val songRepository: SongRepository,
    private val setListRepository: SetListRepository,
    private val musicianRepository: MusicianRepository
) : BaseViewModel() {

    private var _songs = MutableLiveData<List<Song>>()
    val songs: LiveData<List<Song>> = Transformations.map(_songs) { it }

    private var _selectedSongs = MutableLiveData<List<Song>>()
    val selectedSongs: LiveData<List<Song>> = Transformations.map(_selectedSongs) { it }

    private var _musicians = MutableLiveData<List<Musician>>()
    val musicians: LiveData<List<Musician>> = Transformations.map(_musicians) { it }

    private var _selectedMusicians = MutableLiveData<List<Musician>>()
    val selectedMusicians: LiveData<List<Musician>> = Transformations.map(_selectedMusicians) { it }

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

    suspend fun getMusicians() {
        try {
            musicianRepository.getMusicians().let { response ->

                if (response.isSuccessful) {
                    response.body()?.let { musicianList ->
                        _musicians.postValue(musicianList)
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

    suspend fun createSetList() {
        setListRepository.createSetList(
            SetListDTO(
                selectedDateTimestamp,
                _selectedMusicians.value?.map { it.id }.orEmpty(),
                _selectedSongs.value?.map { it.id }.orEmpty()
            )
        )
    }

    fun addSelectedSong(song: Song) {
        val selectedSongs = mutableListOf<Song>().apply {
            addAll(_selectedSongs.value.orEmpty())
            add(song)
        }
        _selectedSongs.postValue(selectedSongs)
    }

    fun addSelectedMusician(musician: Musician) {
        val selectedMusicians = mutableListOf<Musician>().apply {
            addAll(_selectedMusicians.value.orEmpty())
            add(musician)
        }
        _selectedMusicians.postValue(selectedMusicians)
    }

    fun setDateTimestamp(timestamp: Long) {
        selectedDateTimestamp = timestamp
    }

}