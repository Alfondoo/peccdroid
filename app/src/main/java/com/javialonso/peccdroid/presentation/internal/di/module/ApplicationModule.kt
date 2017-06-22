package com.javialonso.peccdroid.presentation.internal.di.module

import android.content.Context
import com.javialonso.peccdroid.data.JobExecutor
import com.javialonso.peccdroid.data.repository.UserAuthenticationRepository
import com.javialonso.peccdroid.data.repository.UserFeedRepository
import com.javialonso.peccdroid.domain.executor.PostExecutionThread
import com.javialonso.peccdroid.domain.executor.ThreadExecutor
import com.javialonso.peccdroid.domain.repository.AuthenticationRepository
import com.javialonso.peccdroid.domain.repository.FeedRepository
import com.javialonso.peccdroid.presentation.AndroidApplication
import com.javialonso.peccdroid.presentation.UIThread
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ApplicationModule(private val application: AndroidApplication) {

    @Provides @Singleton internal fun provideApplicationContext(): Context {
        return this.application
    }

    @Provides @Singleton internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides @Singleton internal fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread {
        return uiThread
    }

    @Provides @Singleton internal fun provideAuthenticationRepository(userAuthenticationRepository: UserAuthenticationRepository): AuthenticationRepository {
        return userAuthenticationRepository
    }

    @Provides @Singleton internal fun provideFeedRepository(userFeedRepository: UserFeedRepository): FeedRepository {
        return userFeedRepository
    }

}