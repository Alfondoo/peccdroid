package com.javialonso.peccdroid.data.repository

import com.javialonso.peccdroid.data.entity.HistoriaCreationEntity
import com.javialonso.peccdroid.data.entity.HistoriaDetailEntity
import com.javialonso.peccdroid.data.entity.HistoriaEntity
import com.javialonso.peccdroid.data.entity.ProfileEntity
import com.javialonso.peccdroid.data.network.RestApi
import com.javialonso.peccdroid.domain.repository.FeedRepository
import io.reactivex.Maybe
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RetrofitFeedRepository @Inject internal constructor(private val restApi: RestApi) : FeedRepository {
    override fun historias(): Maybe<List<HistoriaEntity>> {
        val token = "Token " + restApi.authorizationToken
        return restApi.feedService.historias(token)
    }

    override fun profile(): Maybe<ProfileEntity> {
        val token = "Token " + restApi.authorizationToken
        return restApi.feedService.profile(token)
    }

    override fun detailHistoria(id: Int): Maybe<HistoriaDetailEntity> {
        val token = "Token " + restApi.authorizationToken
        return restApi.feedService.detailHistoria(token, id)
    }

    override fun createHistoria(historia: HistoriaCreationEntity): Maybe<HistoriaDetailEntity> {
        val token = "Token " + restApi.authorizationToken
        return restApi.feedService.createHistoria(token, historia)
    }
}