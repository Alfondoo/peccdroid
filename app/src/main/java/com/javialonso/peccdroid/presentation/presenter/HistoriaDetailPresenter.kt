package com.javialonso.peccdroid.presentation.presenter

import android.support.annotation.NonNull
import com.javialonso.peccdroid.data.entity.HistoriaDetailEntity
import com.javialonso.peccdroid.data.entity.HistoriaEntity
import com.javialonso.peccdroid.domain.interactor.DefaultObserver
import com.javialonso.peccdroid.domain.interactor.HistoriaDetailUseCase
import com.javialonso.peccdroid.presentation.internal.PerActivity
import com.javialonso.peccdroid.presentation.view.fragment.HistoriaDetailView
import javax.inject.Inject

/**
 * Created by Javi on 03/07/2017.
 */
@PerActivity
class HistoriaDetailPresenter
@Inject constructor(private val historiaDetailUseCase: HistoriaDetailUseCase) : Presenter {
    private var historiaDetailView: HistoriaDetailView? = null

    fun setView(@NonNull view: HistoriaDetailView) {
        this.historiaDetailView = view
    }

    override fun resume() {}

    override fun pause() {}

    override fun destroy() {
        this.historiaDetailView = null
    }

    fun historiaDetail(id: Int) {
        this.historiaDetailUseCase.execute(HistoriasObserver(), HistoriaDetailUseCase.Params(id))
    }

    private inner class HistoriasObserver : DefaultObserver<HistoriaDetailEntity>() {

        override fun onError(exception: Throwable) {
            super.onError(exception)
        }

        override fun onComplete() {
        }

        override fun onSuccess(t: HistoriaDetailEntity) {
            updateView(t)
        }

    }

    private fun updateView(historia: HistoriaDetailEntity) {
        this.historiaDetailView?.updateView(historia)
    }
}