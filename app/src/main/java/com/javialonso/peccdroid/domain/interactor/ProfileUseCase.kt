package com.javialonso.peccdroid.domain.interactor

import com.javialonso.peccdroid.data.entity.ProfileEntity
import com.javialonso.peccdroid.domain.executor.PostExecutionThread
import com.javialonso.peccdroid.domain.executor.ThreadExecutor
import com.javialonso.peccdroid.domain.repository.FeedRepository
import io.reactivex.Maybe
import javax.inject.Inject

class ProfileUseCase @Inject internal constructor(val feedRepository: FeedRepository, threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread) : UseCase<ProfileEntity, Unit>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseObservable(params: Unit): Maybe<ProfileEntity> {
        return this.feedRepository.profile()
    }
}