package dev.arildo.appband

import android.app.Application
import dev.arildo.appband.core.di.getSongModules
import dev.arildo.data.di.getDataModules
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