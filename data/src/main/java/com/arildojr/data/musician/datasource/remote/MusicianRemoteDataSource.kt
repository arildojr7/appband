package com.arildojr.data.musician.datasource.remote

import com.arildojr.data.musician.MusicianDataSource
import com.arildojr.data.musician.api.MusicianApiService
import com.arildojr.data.musician.model.Musician
import retrofit2.Response

class MusicianRemoteDataSource(private val apiService: MusicianApiService) : MusicianDataSource.Remote {
    override suspend fun getMusicians(): Response<List<Musician>> {
        return apiService.getMusicians()
    }
}