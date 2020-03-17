package dev.arildo.appband.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import dev.arildo.appband.core.base.BaseViewModel
import dev.arildo.data.login.LoginRepository
import dev.arildo.data.login.dto.LoginDTO
import dev.arildo.data.login.dto.User
import kotlinx.coroutines.delay

class LoginViewModel(private val loginRepository: LoginRepository) : BaseViewModel() {

    private var _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = Transformations.map(_viewState) { it }

    init {
        _viewState.value = ViewState()
    }

    suspend fun login(email: String, password: String) {
        _viewState.value = ViewState(isLogging = true)
        try {
            loginRepository.login(LoginDTO(email, password)).let { response ->
                _viewState.value = ViewState(isLogging = false)

                if (response.isSuccessful) {
                    response.body()?.let { loginResponse ->
                        saveLoggedUser(loginResponse)
                        _viewState.value = ViewState(successLogin = true)
                    }
                } else {
                    _viewState.value = ViewState(isCredentialsInvalid = true)
                }
            }

        } catch (e: Exception) {
            _viewState.value = ViewState(hasNetworkError = true)
            delay(500)
            _viewState.value = ViewState(isLogging = false)
        }

    }

    private suspend fun saveLoggedUser(user: User) {
        loginRepository.saveLoggedUser(user)
    }

    data class ViewState(
        val isLogging: Boolean = false,
        val successLogin: Boolean = false,
        val isCredentialsInvalid: Boolean = false,
        val hasNetworkError: Boolean = false
    )
}