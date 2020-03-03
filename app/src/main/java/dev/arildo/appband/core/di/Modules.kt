package dev.arildo.appband.core.di

import dev.arildo.appband.home.HomeViewModel
import dev.arildo.appband.setlist.SetListViewModel
import dev.arildo.appband.songlist.SongsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val songModules = module {
    viewModel { SetListViewModel(get()) }
    viewModel { SongsViewModel(get()) }
    viewModel { HomeViewModel() }
}

fun getSongModules() = listOf(songModules)