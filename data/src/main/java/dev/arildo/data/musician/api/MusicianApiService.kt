package dev.arildo.data.musician.api

import dev.arildo.data.musician.model.Musician
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface MusicianApiService {

    @GET("musician")
    @Headers("Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI1ZTVhYTQwYmViMDI2NzQ4OTc1NTgyMmQiLCJhdWQiOiJhdWQiLCJhdXRoIjpbIlJPTEVfVVNFUiJdLCJpc3MiOiJpc3MiLCJpYXQiOjE1ODMwMDYyMDZ9.VFbrBMcRpV6gfhvIVk1rZBPZaYnF9XEEqvQ8voftYJFZcZZPe_gALg7vl9i-5ncI1dXV1S5OMKh9LUx1RkU1iw")
    suspend fun getMusicians(): Response<List<Musician>>
}