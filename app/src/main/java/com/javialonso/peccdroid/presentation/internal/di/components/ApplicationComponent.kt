package com.javialonso.peccdroid.presentation.internal.di.components

import android.content.Context
import com.javialonso.peccdroid.domain.executor.PostExecutionThread
import com.javialonso.peccdroid.domain.executor.ThreadExecutor
import com.javialonso.peccdroid.domain.repository.AuthenticationRepository
import com.javialonso.peccdroid.domain.repository.FeedRepository
import com.javialonso.peccdroid.presentation.internal.di.module.ApplicationModule
import com.javialonso.peccdroid.presentation.view.activity.BaseActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun inject(baseActivity: BaseActivity)

    //Exposed to sub-graphs.
    fun context(): Context

    fun threadExecutor(): ThreadExecutor
    fun postExecutionThread(): PostExecutionThread
    fun authenticationRepository(): AuthenticationRepository
    fun feedRepository(): FeedRepository
}