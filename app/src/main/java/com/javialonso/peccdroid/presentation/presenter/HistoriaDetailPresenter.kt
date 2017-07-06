package com.javialonso.peccdroid.presentation.presenter

import android.support.annotation.NonNull
import com.javialonso.peccdroid.data.entity.HistoriaDetailEntity
import com.javialonso.peccdroid.data.storage.TokenStorage
import com.javialonso.peccdroid.domain.interactor.DefaultObserver
import com.javialonso.peccdroid.domain.interactor.HistoriaDetailUseCase
import com.javialonso.peccdroid.presentation.internal.PerActivity
import com.javialonso.peccdroid.presentation.view.fragment.HistoriaDetailView
import javax.inject.Inject

@PerActivity
class HistoriaDetailPresenter
@Inject constructor(private val historiaDetailUseCase: HistoriaDetailUseCase) : Presenter {
    private var historiaDetailView: HistoriaDetailView? = null
    private var tokenStorage: TokenStorage? = null

    fun setView(@NonNull view: HistoriaDetailView) {
        this.historiaDetailView = view
    }

    fun setTokenStorage(@NonNull tokenStorage: TokenStorage) {
        this.tokenStorage = tokenStorage
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
        val usuario = tokenStorage?.retrieveData()?.username
        if (usuario == historia.creador) {
            this.historiaDetailView?.showCreatorControls()
        }
    }
}