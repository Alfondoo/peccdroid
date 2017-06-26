package com.javialonso.peccdroid.data.repository

import com.javialonso.peccdroid.data.entity.ProfileEntity
import com.javialonso.peccdroid.data.network.RestApi
import com.javialonso.peccdroid.domain.repository.FeedRepository
import io.reactivex.Maybe
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserFeedRepository @Inject internal constructor(private val restApi: RestApi) : FeedRepository {
    override fun profile(): Maybe<ProfileEntity> {
        val token = "Token " + restApi.authorizationToken
        return restApi.feedService.profile(token)
    }

}