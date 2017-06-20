package com.javialonso.peccdroid.data.network

import com.javialonso.peccdroid.data.entity.LoginEntity
import com.javialonso.peccdroid.data.entity.RegistrationEntity
import com.javialonso.peccdroid.data.entity.TokenEntity
import io.reactivex.Maybe
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthenticationApi {
    @POST("login/")
    fun login(@Body loginEntity: LoginEntity): Maybe<TokenEntity>

    @POST("registro/")
    fun registro(@Body registrationEntity: RegistrationEntity): Maybe<String>
}