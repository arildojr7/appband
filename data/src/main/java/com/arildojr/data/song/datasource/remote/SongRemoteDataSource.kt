package com.arildojr.data.song.datasource.remote

import com.arildojr.data.song.SongDataSource
import com.arildojr.data.song.api.SongApiService
import com.arildojr.data.song.model.Song
import retrofit2.Response

class SongRemoteDataSource(private val apiService: SongApiService) : SongDataSource.Remote {

    override suspend fun getSongs(): Response<List<Song>> {
        return apiService.getSongs()
    }

}
