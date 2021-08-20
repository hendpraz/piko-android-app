package com.hpdev.piko

import android.app.Application
import com.hpdev.piko.core.di.CoreComponent
import com.hpdev.piko.core.di.DaggerCoreComponent
import com.hpdev.piko.di.AppComponent
import com.hpdev.piko.di.DaggerAppComponent

open class MyApplication : Application() {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }
}