package com.javialonso.peccdroid.data.repository

import com.javialonso.peccdroid.data.entity.LoginEntity
import com.javialonso.peccdroid.data.entity.RegistrationEntity
import com.javialonso.peccdroid.data.entity.TokenEntity
import com.javialonso.peccdroid.data.network.RestApi
import com.javialonso.peccdroid.domain.repository.AuthenticationRepository
import io.reactivex.Maybe
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitAuthenticationRepository @Inject internal constructor(private val restApi: RestApi) : AuthenticationRepository {
    override fun login(user: String, password: String): Maybe<TokenEntity> {
        val loginCredentials = LoginEntity(user, password)
        val maybe = restApi.authenticationService.login(loginCredentials)
        return maybe.doOnSuccess { (token) -> restApi.setAuthorizationToken(token) }
    }

    override fun registro(user: String, email: String, password: String, passwordConfirm: String): Maybe<String> {
        val registrationData = RegistrationEntity(user, email, password, passwordConfirm)
        val maybe = restApi.authenticationService.registro(registrationData)
        return maybe
    }
}
