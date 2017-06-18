package com.javialonso.peccdroid.data.repository

import com.javialonso.peccdroid.data.network.RestApi
import com.javialonso.peccdroid.domain.executor.PostExecutionThread
import com.javialonso.peccdroid.domain.executor.ThreadExecutor
import com.javialonso.peccdroid.domain.repository.AuthenticationRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserAuthenticationRepository @Inject internal constructor(private val restApi: RestApi, val postExecutionThread: PostExecutionThread, val threadExecutor: ThreadExecutor) : AuthenticationRepository {
    override fun login(user: String, password: String): Observable<String> {
        val observable = restApi.authenticationService.login(user, password)
        observable.subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
                .subscribe({ (token) -> restApi.authorizationToken = token }, {}, {})
        return observable.map { (token) -> token }
    }

    override fun registro(user: String, email: String, password: String, passwordConfirm: String): Observable<String> {
        val observable = restApi.authenticationService.registro(user, email, password, passwordConfirm)
        observable.subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
                .subscribe({}, {}, {})
        return observable
    }
}
