package com.arildojr.data.musician

import com.arildojr.data.musician.datasource.remote.MusicianRemoteDataSource
import com.arildojr.data.musician.model.Musician
import retrofit2.Response

class MusicianRepositoryImpl(private val musicianRemoteDataSource: MusicianRemoteDataSource) :
    MusicianRepository {

    override suspend fun getMusicians(): Response<List<Musician>> {
        return musicianRemoteDataSource.getMusicians()
    }

}