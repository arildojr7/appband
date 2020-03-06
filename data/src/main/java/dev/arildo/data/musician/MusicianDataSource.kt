package dev.arildo.data.musician

import dev.arildo.data.musician.model.Musician
import retrofit2.Response

interface MusicianDataSource {

    interface Remote {
        suspend fun getMusicians(): Response<List<Musician>>
    }
}