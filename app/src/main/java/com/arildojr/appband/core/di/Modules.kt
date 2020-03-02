package com.arildojr.appband.core.di

import com.arildojr.appband.home.HomeViewModel
import com.arildojr.appband.setlist.SetListViewModel
import com.arildojr.appband.songlist.SongsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val songModules = module {
    viewModel { SetListViewModel(get()) }
    viewModel { SongsViewModel(get()) }
    viewModel { HomeViewModel() }
}

fun getSongModules() = listOf(songModules)
