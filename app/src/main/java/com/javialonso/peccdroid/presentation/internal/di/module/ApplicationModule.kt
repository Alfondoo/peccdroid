package com.javialonso.peccdroid.presentation.internal.di.module

import android.content.Context
import com.javialonso.peccdroid.data.JobExecutor
import com.javialonso.peccdroid.data.repository.RetrofitAuthenticationRepository
import com.javialonso.peccdroid.data.repository.RetrofitFeedRepository
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

    @Provides @Singleton internal fun provideAuthenticationRepository(retrofitAuthenticationRepository: RetrofitAuthenticationRepository): AuthenticationRepository {
        return retrofitAuthenticationRepository
    }

    @Provides @Singleton internal fun provideFeedRepository(retrofitFeedRepository: RetrofitFeedRepository): FeedRepository {
        return retrofitFeedRepository
    }

}