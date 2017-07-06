package com.javialonso.peccdroid.presentation.presenter

import android.support.annotation.NonNull
import android.util.Log
import com.javialonso.peccdroid.data.entity.AportePadreEntity
import com.javialonso.peccdroid.data.entity.HistoriaCreationEntity
import com.javialonso.peccdroid.data.entity.HistoriaDetailEntity
import com.javialonso.peccdroid.domain.interactor.DefaultObserver
import com.javialonso.peccdroid.domain.interactor.HistoriaCreateUseCase
import com.javialonso.peccdroid.presentation.internal.PerActivity
import com.javialonso.peccdroid.presentation.view.fragment.HistoriaCreateView
import javax.inject.Inject

@PerActivity
class HistoriaCreatePresenter
@Inject constructor(private val historiaCreateUseCase: HistoriaCreateUseCase) : Presenter {
    private var historiaCreateView: HistoriaCreateView? = null

    fun setView(@NonNull view: HistoriaCreateView) {
        this.historiaCreateView = view
    }

    override fun resume() {}

    override fun pause() {}

    override fun destroy() {
        this.historiaCreateView = null
    }

    fun historiaCreate(titulo: String, contenido: String, esBifurcable: Boolean) {
        val historia = HistoriaCreationEntity(titulo = titulo, aporte = AportePadreEntity(contenido = contenido, esBifurcable = esBifurcable))
        this.historiaCreateUseCase.execute(HistoriasCreateObserver(), HistoriaCreateUseCase.Params(historia))
    }

    private inner class HistoriasCreateObserver : DefaultObserver<HistoriaDetailEntity>() {

        override fun onError(exception: Throwable) {
            super.onError(exception)
        }

        override fun onComplete() {
        }

        override fun onSuccess(t: HistoriaDetailEntity) {
            creationComplete(t)
        }

    }

    private fun creationComplete(historia: HistoriaDetailEntity) {
        Log.d("HistoriaCreate", historia.toString())
    }
}
