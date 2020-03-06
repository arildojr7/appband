package dev.arildo.data.musician.datasource.remote

import dev.arildo.data.musician.MusicianDataSource
import dev.arildo.data.musician.api.MusicianApiService
import dev.arildo.data.musician.model.Musician
import retrofit2.Response

class MusicianRemoteDataSource(private val apiService: MusicianApiService) : MusicianDataSource.Remote {
    override suspend fun getMusicians(): Response<List<Musician>> {
        return apiService.getMusicians()
    }
}