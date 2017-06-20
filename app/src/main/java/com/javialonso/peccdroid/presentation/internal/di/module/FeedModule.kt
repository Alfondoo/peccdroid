package com.javialonso.peccdroid.presentation.internal.di.module

import com.javialonso.peccdroid.data.repository.UserFeedRepository
import com.javialonso.peccdroid.domain.repository.FeedRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FeedModule {
    @Provides @Singleton internal fun provideFeedRepository(userFeedRepository: UserFeedRepository): FeedRepository {
        return userFeedRepository
    }
}