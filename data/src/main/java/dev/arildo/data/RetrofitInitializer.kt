package dev.arildo.data

import dev.arildo.data.login.api.LoginApiService
import dev.arildo.data.musician.api.MusicianApiService
import dev.arildo.data.setlist.api.SetListApiService
import dev.arildo.data.song.api.SongApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInitializer {

    companion object {
        private const val TIMEOUT: Long = 60
    }

    private fun logInterceptor(): HttpLoggingInterceptor {
        val logInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return logInterceptor
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logInterceptor())
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS).build()
    }

    private fun createNetworkClient(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(createOkHttpClient())
        .build()

    fun songApiService(): SongApiService =
        createNetworkClient().create(SongApiService::class.java)

    fun setListApiService(): SetListApiService =
        createNetworkClient().create(SetListApiService::class.java)

    fun musicianApiService(): MusicianApiService =
        createNetworkClient().create(MusicianApiService::class.java)

    fun loginApiService(): LoginApiService =
        createNetworkClient().create(LoginApiService::class.java)

}