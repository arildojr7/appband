package com.arildojr.appband

import android.app.Application
import com.arildojr.appband.core.di.getSongModules
import com.arildojr.data.di.getDataModules
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)

        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(getDataModules() + getSongModules())
        }
    }
}