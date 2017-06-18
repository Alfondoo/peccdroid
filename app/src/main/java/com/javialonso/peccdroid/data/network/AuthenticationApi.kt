package com.javialonso.peccdroid.data.network

import com.javialonso.peccdroid.data.entity.TokenEntity
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface AuthenticationApi {
    @FormUrlEncoded
    @POST("login/")
    fun login(@Field("username") user: String, @Field("password") password: String): Observable<TokenEntity>

    @FormUrlEncoded
    @POST("registro/")
    fun registro(@Field("username") user: String, @Field("email") email: String, @Field("password") password: String, @Field("confirmacion_password") passwordConfirm: String): Observable<String>
}