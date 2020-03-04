package dev.arildo.appband.core.di

import dev.arildo.appband.home.viewmodel.HomeViewModel
import dev.arildo.appband.setlist.viewmodel.SetListSetViewModel
import dev.arildo.appband.setlist.viewmodel.SetListViewModel
import dev.arildo.appband.songlist.viewmodel.SongsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val songModules = module {
    viewModel { SetListViewModel(get()) }
    viewModel { SetListSetViewModel(get(), get(), get()) }
    viewModel { SongsViewModel(get()) }
    viewModel { HomeViewModel() }
}

fun getSongModules() = listOf(songModules)
