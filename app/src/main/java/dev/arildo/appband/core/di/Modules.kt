package dev.arildo.appband.core.di

import dev.arildo.appband.home.viewmodel.HomeViewModel
import dev.arildo.appband.login.viewmodel.LoginViewModel
import dev.arildo.appband.profile.viewmodel.ProfileViewModel
import dev.arildo.appband.setlist.viewmodel.SetListSetViewModel
import dev.arildo.appband.setlist.viewmodel.SetListViewModel
import dev.arildo.appband.song.viewmodel.SongsViewModel
import dev.arildo.appband.splash.viewmodel.SplashViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val songModules = module {
    viewModel { LoginViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { SetListViewModel(get()) }
    viewModel { SetListSetViewModel(get(), get(), get()) }
    viewModel { SongsViewModel(get()) }
    viewModel { HomeViewModel() }
}

fun getSongModules() = listOf(songModules)
