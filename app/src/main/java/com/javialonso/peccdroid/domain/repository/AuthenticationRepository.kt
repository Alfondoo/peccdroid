package com.javialonso.peccdroid.domain.repository

import io.reactivex.Maybe


interface AuthenticationRepository {
    fun login(user: String, password: String): Maybe<String>
    fun registro(user: String, email: String, password: String, passwordConfirm: String): Maybe<String>
}
