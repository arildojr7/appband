package dev.arildo.appband.profile.viewmodel

import android.util.Base64
import androidx.lifecycle.MutableLiveData
import dev.arildo.appband.core.base.BaseViewModel
import dev.arildo.data.login.LoginRepository
import dev.arildo.data.login.model.User
import kotlinx.coroutines.launch

class ProfileViewModel(private val loginRepository: LoginRepository) : BaseViewModel() {

    var user: MutableLiveData<User> = MutableLiveData()

    init {
        launch {
            user.postValue(loginRepository.getLoggedUser())
        }
    }

    suspend fun logout() {
        loginRepository.logout()
    }

    fun hexStringToByteArray(string: String?): ByteArray? {
        val s = string?.split(",")?.last().orEmpty()
        return Base64.decode(s, Base64.DEFAULT)
    }
}
