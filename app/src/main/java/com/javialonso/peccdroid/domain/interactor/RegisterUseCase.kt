package com.javialonso.peccdroid.domain.interactor

import android.support.v4.util.Preconditions
import com.javialonso.peccdroid.domain.executor.PostExecutionThread
import com.javialonso.peccdroid.domain.executor.ThreadExecutor
import com.javialonso.peccdroid.domain.repository.AuthenticationRepository
import io.reactivex.Observable
import javax.inject.Inject

class RegisterUseCase @Inject internal constructor(val registerRepository: AuthenticationRepository, threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread) : UseCase<String, RegisterUseCase.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Observable<String> {
        Preconditions.checkNotNull(params)
        return this.registerRepository.registro(params.username, params.email, params.password, params.passwordConfirmation)
    }

    data class Params(val username: String, val email: String, val password: String, val passwordConfirmation: String)
}