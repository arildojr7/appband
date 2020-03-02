package com.arildojr.data.setlist.api

import com.arildojr.data.setlist.model.SetList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface SetListApiService {

    @GET("setlist")
    @Headers("Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI1ZTVhYTQwYmViMDI2NzQ4OTc1NTgyMmQiLCJhdWQiOiJhdWQiLCJhdXRoIjpbIlJPTEVfVVNFUiJdLCJpc3MiOiJpc3MiLCJpYXQiOjE1ODMwMDYyMDZ9.VFbrBMcRpV6gfhvIVk1rZBPZaYnF9XEEqvQ8voftYJFZcZZPe_gALg7vl9i-5ncI1dXV1S5OMKh9LUx1RkU1iw")
    suspend fun getSetLists(): Response<List<SetList>>
}