package dev.arildo.appband.profile.viewmodel

import dev.arildo.appband.core.base.BaseViewModel
import dev.arildo.data.login.LoginRepository

class ProfileViewModel(private val loginRepository: LoginRepository) : BaseViewModel() {

    suspend fun logout() {
        loginRepository.logout()
    }
}
