package com.javialonso.peccdroid.data.repository

import com.javialonso.peccdroid.data.network.RestApi
import com.javialonso.peccdroid.domain.repository.FeedRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserFeedRepository @Inject internal constructor(private val restApi: RestApi) : FeedRepository {

}