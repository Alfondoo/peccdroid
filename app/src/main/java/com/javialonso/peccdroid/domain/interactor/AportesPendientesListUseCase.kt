package com.javialonso.peccdroid.domain.interactor

import android.support.v4.util.Preconditions
import com.javialonso.peccdroid.data.entity.AporteDetailEntity
import com.javialonso.peccdroid.domain.executor.PostExecutionThread
import com.javialonso.peccdroid.domain.executor.ThreadExecutor
import com.javialonso.peccdroid.domain.repository.FeedRepository
import io.reactivex.Maybe
import javax.inject.Inject

class AportesPendientesListUseCase @Inject internal constructor(val feedRepository: FeedRepository, threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread) : UseCase<List<AporteDetailEntity>, AportesPendientesListUseCase.Params>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseObservable(params: Params): Maybe<List<AporteDetailEntity>> {
        Preconditions.checkNotNull(params)
        return this.feedRepository.aportesPendientes(params.historia)
    }

    data class Params(val historia: Int)
}
