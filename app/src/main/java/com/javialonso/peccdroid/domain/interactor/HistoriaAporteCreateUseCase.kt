package com.javialonso.peccdroid.domain.interactor

import android.support.v4.util.Preconditions
import com.javialonso.peccdroid.data.entity.AporteCreationEntity
import com.javialonso.peccdroid.data.entity.AporteDetailEntity
import com.javialonso.peccdroid.domain.executor.PostExecutionThread
import com.javialonso.peccdroid.domain.executor.ThreadExecutor
import com.javialonso.peccdroid.domain.repository.FeedRepository
import io.reactivex.Maybe
import javax.inject.Inject


class HistoriaAporteCreateUseCase @Inject internal constructor(val feedRepository: FeedRepository, threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread) : UseCase<AporteDetailEntity, HistoriaAporteCreateUseCase.Params>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseObservable(params: Params): Maybe<AporteDetailEntity> {
        Preconditions.checkNotNull(params)
        return this.feedRepository.createAporteHistoria(params.aporte, params.aportePadre, params.historia)
    }

    data class Params(val aporte: AporteCreationEntity, val aportePadre: Int, val historia: Int)
}

