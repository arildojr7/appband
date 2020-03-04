package com.arildojr.data.musician

import com.arildojr.data.musician.model.Musician
import retrofit2.Response

interface MusicianRepository {
    suspend fun getMusicians(): Response<List<Musician>>
}