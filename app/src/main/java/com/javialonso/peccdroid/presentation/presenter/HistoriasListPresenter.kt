package com.javialonso.peccdroid.presentation.presenter

import android.support.annotation.NonNull
import com.javialonso.peccdroid.data.entity.HistoriaEntity
import com.javialonso.peccdroid.domain.interactor.DefaultObserver
import com.javialonso.peccdroid.domain.interactor.HistoriasListUseCase
import com.javialonso.peccdroid.presentation.internal.PerActivity
import com.javialonso.peccdroid.presentation.view.contract.HistoriasView
import javax.inject.Inject

@PerActivity
class HistoriasListPresenter
@Inject constructor(val historiasListUseCase: HistoriasListUseCase) : Presenter {

    private var historiasView: HistoriasView? = null

    fun setView(@NonNull view: HistoriasView) {
        this.historiasView = view
    }

    override fun resume() {}

    override fun pause() {}

    override fun destroy() {
        // this.historiasListUseCase.dispose()
        this.historiasView = null
    }

    fun historias() {
        this.historiasListUseCase.execute(HistoriasObserver(), Unit)
    }

    fun onHistoriaClicked(historia: HistoriaEntity) {
        this.historiasView?.toDetailHistoria(historia)
    }

    fun createHistoria(){
        this.historiasView?.toCreateHistoria()
    }

    private inner class HistoriasObserver : DefaultObserver<List<HistoriaEntity>>() {

        override fun onError(exception: Throwable) {
            super.onError(exception)
        }

        override fun onComplete() {
        }

        override fun onSuccess(t: List<HistoriaEntity>) {
            updateView(t)
        }

    }

    private fun updateView(historias: List<HistoriaEntity>) {
        this.historiasView?.updateHistoriasList(historias)
    }
}
