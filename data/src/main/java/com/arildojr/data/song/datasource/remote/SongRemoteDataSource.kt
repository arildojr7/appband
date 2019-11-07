package com.arildojr.data.song.datasource.remote

import com.arildojr.data.song.SongDataSource
import com.arildojr.data.song.api.SongApiService
import com.arildojr.data.song.model.Song

class SongRemoteDataSource(private val apiService: SongApiService) : SongDataSource.Remote {

    override suspend fun getSongs(): List<Song> {
        return apiService.getSongs().body()!!
    }

}
