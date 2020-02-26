package com.arildojr.data.song

import com.arildojr.data.song.model.Song
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface SongRepository {

    fun getSongs() : Flow<Response<List<Song>>>

    fun getSongsFiltered(filter: String): Flow<Response<List<Song>>>

    fun insertAll(songs: List<Song>)
}