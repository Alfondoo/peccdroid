package com.javialonso.peccdroid.data.network

import com.javialonso.peccdroid.data.entity.ProfileEntity
import io.reactivex.Maybe
import retrofit2.http.GET
import retrofit2.http.Header

interface FeedApi {
    @GET("perfil/")
    fun profile(@Header("Authorization") token: String): Maybe<ProfileEntity>
}