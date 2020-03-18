package dev.arildo.data.di

import android.content.Context
import android.content.SharedPreferences
import dev.arildo.data.RetrofitInitializer
import dev.arildo.data.login.LoginRepository
import dev.arildo.data.login.LoginRepositoryImpl
import dev.arildo.data.login.datasource.local.LoginLocalDataSource
import dev.arildo.data.login.datasource.remote.LoginRemoteDataSource
import dev.arildo.data.musician.MusicianRepository
import dev.arildo.data.musician.MusicianRepositoryImpl
import dev.arildo.data.musician.datasource.remote.MusicianRemoteDataSource
import dev.arildo.data.setlist.SetListRepository
import dev.arildo.data.setlist.SetListRepositoryImpl
import dev.arildo.data.setlist.datasource.remote.SetListRemoteDataSource
import dev.arildo.data.song.SongRepository
import dev.arildo.data.song.SongRepositoryImpl
import dev.arildo.data.song.datasource.local.SongLocalDataSource
import dev.arildo.data.AppDatabase
import dev.arildo.data.setlist.datasource.local.SetListLocalDataSource
import dev.arildo.data.song.datasource.remote.SongRemoteDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private val dbModule = module {
    single { AppDatabase(get()) }
    single { get<AppDatabase>().songDao() }
    single { get<AppDatabase>().setListDao() }
}

private val apiServiceModule = module {
    single { RetrofitInitializer().songApiService() }
    single { RetrofitInitializer().setListApiService() }
    single { RetrofitInitializer().musicianApiService() }
    single { RetrofitInitializer().loginApiService() }
}

private val repositoryModule = module {
    single<LoginRepository> { LoginRepositoryImpl(get(), get()) }
    single<SongRepository> { SongRepositoryImpl(get(), get()) }
    single<SetListRepository> { SetListRepositoryImpl(get(), get()) }
    single<MusicianRepository> { MusicianRepositoryImpl(get()) }
}

private val dataSourceModule = module {
    single { LoginLocalDataSource(get()) }
    single { LoginRemoteDataSource(get()) }
    single { SongRemoteDataSource(get()) }
    single { SongLocalDataSource(get()) }
    single { SetListRemoteDataSource(get()) }
    single { SetListLocalDataSource(get()) }
    single { MusicianRemoteDataSource(get()) }
}

private val sharedPreferences = module {
    single<SharedPreferences> { androidContext().getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE) }
}

fun getDataModules() = listOf(apiServiceModule, repositoryModule, dataSourceModule, dbModule, sharedPreferences)