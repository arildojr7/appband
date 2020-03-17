package dev.arildo.data.login

import dev.arildo.data.login.datasource.local.LoginLocalDataSource
import dev.arildo.data.login.datasource.remote.LoginRemoteDataSource
import dev.arildo.data.login.dto.LoginDTO
import dev.arildo.data.login.dto.User
import retrofit2.Response

class LoginRepositoryImpl(
    private val localDataSource: LoginLocalDataSource,
    private val remoteDataSource: LoginRemoteDataSource
) : LoginRepository {

    override suspend fun isUserLogged(): Boolean {
        return localDataSource.isUserLogged()
    }

    override suspend fun login(loginDTO: LoginDTO): Response<User> {
        return remoteDataSource.login(loginDTO)
    }

    override suspend fun saveLoggedUser(user: User) {
        localDataSource.saveLoggedUser(user)
    }

    override suspend fun logout() {
        localDataSource.logout()
    }

}