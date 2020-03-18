package dev.arildo.data.login.api

import dev.arildo.data.BuildConfig
import dev.arildo.data.login.dto.LoginDTO
import dev.arildo.data.login.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginApiService {

    @POST("login")
    @Headers("Authorization: ${BuildConfig.BASIC_API}")
    suspend fun login(@Body loginDTO: LoginDTO): Response<User>
}