package dev.arildo.data.song.datasource.remote

import dev.arildo.data.song.SongDataSource
import dev.arildo.data.song.api.SongApiService
import dev.arildo.data.song.model.Song
import retrofit2.Response

class SongRemoteDataSource(private val apiService: SongApiService) : SongDataSource.Remote {

    override suspend fun getSongs(): Response<List<Song>> {
        return apiService.getSongs()
    }

}
