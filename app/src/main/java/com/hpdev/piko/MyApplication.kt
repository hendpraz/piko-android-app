package com.hpdev.piko

import android.app.Application
import com.hpdev.piko.core.di.databaseModule
import com.hpdev.piko.core.di.networkModule
import com.hpdev.piko.core.di.repositoryModule
import com.hpdev.piko.di.useCaseModule
import com.hpdev.piko.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}