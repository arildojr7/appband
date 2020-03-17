package dev.arildo.appband.splash.viewmodel

import dev.arildo.appband.core.base.BaseViewModel
import dev.arildo.data.login.LoginRepository

class SplashViewModel(private val loginRepository: LoginRepository) : BaseViewModel() {

    suspend fun isUserLogged(): Boolean {
        return loginRepository.isUserLogged()
    }
}