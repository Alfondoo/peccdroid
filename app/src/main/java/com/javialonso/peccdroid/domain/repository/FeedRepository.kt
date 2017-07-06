package com.javialonso.peccdroid.domain.repository

import com.javialonso.peccdroid.data.entity.HistoriaCreationEntity
import com.javialonso.peccdroid.data.entity.HistoriaDetailEntity
import com.javialonso.peccdroid.data.entity.HistoriaEntity
import com.javialonso.peccdroid.data.entity.ProfileEntity
import io.reactivex.Maybe

interface FeedRepository {
    fun profile(): Maybe<ProfileEntity>
    fun historias(): Maybe<List<HistoriaEntity>>
    fun detailHistoria(id: Int): Maybe<HistoriaDetailEntity>
    fun createHistoria(historia: HistoriaCreationEntity): Maybe<HistoriaDetailEntity>
}