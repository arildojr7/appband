package com.arildojr.data.song.api

import com.arildojr.data.song.model.Song
import retrofit2.Response
import retrofit2.http.GET

interface SongApiService {

    @GET("/songs")
    suspend fun getSongs(): Response<List<Song>>
}