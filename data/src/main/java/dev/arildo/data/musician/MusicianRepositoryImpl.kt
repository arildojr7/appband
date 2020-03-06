package dev.arildo.data.musician

import dev.arildo.data.musician.datasource.remote.MusicianRemoteDataSource
import dev.arildo.data.musician.model.Musician
import retrofit2.Response

class MusicianRepositoryImpl(private val musicianRemoteDataSource: MusicianRemoteDataSource) :
    MusicianRepository {

    override suspend fun getMusicians(): Response<List<Musician>> {
        return musicianRemoteDataSource.getMusicians()
    }

}