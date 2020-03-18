package dev.arildo.data.login

import dev.arildo.data.login.dto.LoginDTO
import dev.arildo.data.login.model.User
import retrofit2.Response

interface LoginRepository {

    suspend fun isUserLogged(): Boolean

    suspend fun getLoggedUser(): User?

    suspend fun login(loginDTO: LoginDTO): Response<User>

    suspend fun saveLoggedUser(user: User)

    suspend fun logout()
}