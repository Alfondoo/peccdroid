package com.javialonso.peccdroid.domain.interactor

import android.support.v4.util.Preconditions
import com.javialonso.peccdroid.data.entity.HistoriaEntity
import com.javialonso.peccdroid.domain.executor.PostExecutionThread
import com.javialonso.peccdroid.domain.executor.ThreadExecutor
import com.javialonso.peccdroid.domain.repository.FeedRepository
import io.reactivex.Maybe
import javax.inject.Inject

class HistoriasListUseCase @Inject internal constructor(val feedRepository: FeedRepository, threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread) : UseCase<List<HistoriaEntity>, Unit>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseObservable(params: Unit): Maybe<List<HistoriaEntity>> {
        Preconditions.checkNotNull(params)
        return this.feedRepository.historias()
    }
}