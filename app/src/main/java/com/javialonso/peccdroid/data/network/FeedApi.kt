package com.javialonso.peccdroid.data.network

import com.javialonso.peccdroid.data.entity.HistoriaDetailEntity
import com.javialonso.peccdroid.data.entity.HistoriaEntity
import com.javialonso.peccdroid.data.entity.ProfileEntity
import io.reactivex.Maybe
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface FeedApi {
    @GET("perfil/")
    fun profile(@Header("Authorization") token: String): Maybe<ProfileEntity>

    @GET("historias/")
    fun historias(@Header("Authorization") token: String): Maybe<List<HistoriaEntity>>

    @GET("historias/{id}")
    fun detailHistoria(@Header("Authorization") token: String, @Path("id") id: Int): Maybe<HistoriaDetailEntity>
}