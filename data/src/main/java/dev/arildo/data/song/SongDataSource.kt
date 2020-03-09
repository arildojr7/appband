package dev.arildo.data.song

import dev.arildo.data.song.model.Song
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface SongDataSource {
    interface Local {
        fun getSongs(): Flow<List<Song>>

        fun getSongsFiltered(filter: String): Flow<List<Song>>

        fun insertAll(songs: List<Song>)
    }

    interface Remote {
        suspend fun getSongs(): Response<List<Song>>
    }
}