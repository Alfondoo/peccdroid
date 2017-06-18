package com.javialonso.peccdroid.domain.repository

import io.reactivex.Observable


interface AuthenticationRepository {
    fun login(user: String, password: String): Observable<String>
    fun registro(user: String, email: String, password: String, passwordConfirm: String): Observable<String>
}
