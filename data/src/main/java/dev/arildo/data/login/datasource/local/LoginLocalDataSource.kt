package dev.arildo.data.login.datasource.local

import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import dev.arildo.data.login.LoginDataSource
import dev.arildo.data.login.dto.User

class LoginLocalDataSource(private val sharedPref: SharedPreferences) : LoginDataSource.Local {

    override suspend fun isUserLogged(): Boolean {
        return sharedPref.contains(KEY_USER)
    }

    override suspend fun saveLoggedUser(user: User) {
        put(user, KEY_USER)
    }

    override suspend fun logout() {
        sharedPref.edit().clear().apply()
    }

    private fun <T> put(`object`: T, key: String) {
        val jsonString = GsonBuilder().create().toJson(`object`)
        sharedPref.edit().putString(key, jsonString).apply()
    }

    private inline fun <reified T> get(key: String): T? {
        val value = sharedPref.getString(key, null)
        return GsonBuilder().create().fromJson(value, T::class.java)
    }

    companion object {
        private const val KEY_USER = "user"
    }

}