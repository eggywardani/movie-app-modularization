package com.eggy

import android.app.Application
import com.eggy.movieapp.core.di.databaseModule
import com.eggy.movieapp.core.di.networkModule
import com.eggy.movieapp.core.di.repositoryModule
import com.eggy.movieapp.di.useCaseModule
import com.eggy.movieapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApp)
            modules(
                listOf(
                    repositoryModule,
                    networkModule,
                    useCaseModule,
                    viewModelModule,
                    databaseModule
                )
            )
        }
    }
}