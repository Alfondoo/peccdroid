package com.javialonso.peccdroid.domain.interactor

import android.support.v4.util.Preconditions
import com.javialonso.peccdroid.data.entity.AporteDetailEntity
import com.javialonso.peccdroid.domain.executor.PostExecutionThread
import com.javialonso.peccdroid.domain.executor.ThreadExecutor
import com.javialonso.peccdroid.domain.repository.FeedRepository
import io.reactivex.Maybe
import javax.inject.Inject

class ValidateAportesUseCase @Inject internal constructor(val feedRepository: FeedRepository, threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread) : UseCase<AporteDetailEntity, ValidateAportesUseCase.Params>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseObservable(params: Params): Maybe<AporteDetailEntity> {
        Preconditions.checkNotNull(params)
        return this.feedRepository.validar(params.historia, params.aporte, params.validacion)
    }

    data class Params(val historia: Int, val aporte: Int, val validacion: Boolean)
}
