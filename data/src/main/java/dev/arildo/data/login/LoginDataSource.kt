package dev.arildo.data.login

import dev.arildo.data.login.dto.LoginDTO
import dev.arildo.data.login.dto.User
import retrofit2.Response

interface LoginDataSource {

    interface Local {
        suspend fun isUserLogged() : Boolean

        suspend fun saveLoggedUser(user: User)

        suspend fun logout()
    }
    interface Remote {
        suspend fun login(loginDTO: LoginDTO): Response<User>
    }
}