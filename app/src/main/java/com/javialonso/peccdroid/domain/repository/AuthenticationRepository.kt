package com.javialonso.peccdroid.domain.repository

import com.javialonso.peccdroid.data.entity.TokenEntity
import io.reactivex.Maybe


interface AuthenticationRepository {
    fun login(user: String, password: String): Maybe<TokenEntity>
    fun registro(user: String, email: String, password: String, passwordConfirm: String): Maybe<String>
}
