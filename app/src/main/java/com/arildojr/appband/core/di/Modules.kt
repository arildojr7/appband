package com.arildojr.appband.core.di

import com.arildojr.appband.songlist.SongViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val songModules = module {
    viewModel { SongViewModel(get()) }
}

fun getSongModules() = listOf(songModules)
