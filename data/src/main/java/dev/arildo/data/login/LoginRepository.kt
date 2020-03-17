package dev.arildo.data.login

import dev.arildo.data.login.dto.LoginDTO
import dev.arildo.data.login.dto.User
import retrofit2.Response

interface LoginRepository {

    suspend fun isUserLogged() : Boolean

    suspend fun login(loginDTO: LoginDTO): Response<User>

    suspend fun saveLoggedUser(user: User)

    suspend fun logout()
}