package com.arildojr.data.di

import com.arildojr.data.RetrofitInitializer
import com.arildojr.data.song.SongRepository
import com.arildojr.data.song.SongRepositoryImpl
import com.arildojr.data.song.datasource.local.SongLocalDataSource
import com.arildojr.data.song.datasource.local.database.SongDatabase
import com.arildojr.data.song.datasource.remote.SongRemoteDataSource
import org.koin.dsl.module

private val dbModule = module {
    single { SongDatabase(get()) }
    single { get<SongDatabase>().songDao() }
}

private val apiServiceModule = module {
    single { RetrofitInitializer().songApiService() }
}

private val repositoryModule = module {
    single<SongRepository> { SongRepositoryImpl(get(), get()) }
}

private val dataSourceModule = module {
    single { SongRemoteDataSource(get()) }
    single { SongLocalDataSource(get()) }
}

fun getDataModules() = listOf(apiServiceModule, repositoryModule, dataSourceModule, dbModule)