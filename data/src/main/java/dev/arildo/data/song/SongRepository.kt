package dev.arildo.data.song

import dev.arildo.data.song.model.Song
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface SongRepository {

    fun getSongs() : Flow<Response<List<Song>>>

    fun getSongsFiltered(filter: String): Flow<Response<List<Song>>>

    fun insertAll(songs: List<Song>)
}