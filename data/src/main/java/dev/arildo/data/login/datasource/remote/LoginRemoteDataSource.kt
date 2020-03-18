package dev.arildo.data.login.datasource.remote

import dev.arildo.data.login.LoginDataSource
import dev.arildo.data.login.api.LoginApiService
import dev.arildo.data.login.dto.LoginDTO
import dev.arildo.data.login.model.User
import retrofit2.Response

class LoginRemoteDataSource(private val apiService: LoginApiService) : LoginDataSource.Remote {
    override suspend fun login(loginDTO: LoginDTO): Response<User> {
        return apiService.login(loginDTO)
    }
}