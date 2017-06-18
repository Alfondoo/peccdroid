package com.javialonso.peccdroid.presentation

import android.app.Application
import com.javialonso.peccdroid.BuildConfig
import com.javialonso.peccdroid.presentation.internal.di.components.ApplicationComponent
import com.javialonso.peccdroid.presentation.internal.di.components.DaggerApplicationComponent
import com.javialonso.peccdroid.presentation.internal.di.module.ApplicationModule
import com.squareup.leakcanary.LeakCanary


class AndroidApplication : Application() {

    var applicationComponent: ApplicationComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        this.initializeInjector()
        this.initializeLeakDetection()
    }

    private fun initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    private fun initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this)
        }
    }
}