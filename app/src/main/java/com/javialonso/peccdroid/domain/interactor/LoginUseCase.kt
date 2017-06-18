package com.javialonso.peccdroid.domain.interactor

import android.support.v4.util.Preconditions
import com.javialonso.peccdroid.domain.executor.PostExecutionThread
import com.javialonso.peccdroid.domain.executor.ThreadExecutor
import com.javialonso.peccdroid.domain.interactor.UseCase
import com.javialonso.peccdroid.domain.repository.AuthenticationRepository
import io.reactivex.Observable
import javax.inject.Inject

class LoginUseCase @Inject internal constructor(val loginRepository: AuthenticationRepository, threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread) : UseCase<String, LoginUseCase.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Observable<String> {
        Preconditions.checkNotNull(params)
        return this.loginRepository.login(params.username, params.password)
    }

    data class Params(val username: String, val password: String)
}