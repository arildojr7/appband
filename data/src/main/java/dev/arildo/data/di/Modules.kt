package dev.arildo.data.di

import dev.arildo.data.RetrofitInitializer
import dev.arildo.data.musician.MusicianRepository
import dev.arildo.data.musician.MusicianRepositoryImpl
import dev.arildo.data.musician.datasource.remote.MusicianRemoteDataSource
import dev.arildo.data.setlist.SetListRepository
import dev.arildo.data.setlist.SetListRepositoryImpl
import dev.arildo.data.setlist.datasource.remote.SetListRemoteDataSource
import dev.arildo.data.song.SongRepository
import dev.arildo.data.song.SongRepositoryImpl
import dev.arildo.data.song.datasource.local.SongLocalDataSource
import dev.arildo.data.song.datasource.local.database.SongDatabase
import dev.arildo.data.song.datasource.remote.SongRemoteDataSource
import org.koin.dsl.module

private val dbModule = module {
    single { SongDatabase(get()) }
    single { get<SongDatabase>().songDao() }
}

private val apiServiceModule = module {
    single { RetrofitInitializer().songApiService() }
    single { RetrofitInitializer().setListApiService() }
    single { RetrofitInitializer().musicianApiService() }
}

private val repositoryModule = module {
    single<SongRepository> { SongRepositoryImpl(get(), get()) }
    single<SetListRepository> { SetListRepositoryImpl(get()) }
    single<MusicianRepository> { MusicianRepositoryImpl(get()) }
}

private val dataSourceModule = module {
    single { SongRemoteDataSource(get()) }
    single { SongLocalDataSource(get()) }
    single { SetListRemoteDataSource(get()) }
    single { MusicianRemoteDataSource(get()) }
}

fun getDataModules() = listOf(apiServiceModule, repositoryModule, dataSourceModule, dbModule)