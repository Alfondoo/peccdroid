package com.javialonso.peccdroid.presentation.presenter

import android.support.annotation.NonNull
import com.javialonso.peccdroid.data.entity.AporteCreationEntity
import com.javialonso.peccdroid.data.entity.AporteDetailEntity
import com.javialonso.peccdroid.data.entity.HistoriaDetailEntity
import com.javialonso.peccdroid.domain.interactor.DefaultObserver
import com.javialonso.peccdroid.domain.interactor.HistoriaAporteCreateUseCase
import com.javialonso.peccdroid.presentation.internal.PerActivity
import com.javialonso.peccdroid.presentation.view.contract.HistoriaAporteCreateView
import javax.inject.Inject

@PerActivity
class HistoriaAporteCreatePresenter
@Inject constructor(private val historiaAporteCreateUseCase: HistoriaAporteCreateUseCase) : Presenter {

    private var historiaAporteCreateView: HistoriaAporteCreateView? = null
    private var aporte: AporteDetailEntity? = null
    private var historia: HistoriaDetailEntity? = null

    fun setView(@NonNull view: HistoriaAporteCreateView) {
        this.historiaAporteCreateView = view
    }

    fun setAportePadre(@NonNull aporte: AporteDetailEntity) {
        this.aporte = aporte
    }

    fun setHistoria(@NonNull historia: HistoriaDetailEntity) {
        this.historia = historia
    }

    override fun resume() {}

    override fun pause() {}

    override fun destroy() {
        this.historiaAporteCreateView = null
    }

    fun historiaAporteCreate(contenido: String, esBifurcable: Boolean) {
        this.aporte?.let { aporteSafe ->
            this.historia?.let { historiaSafe ->
                this.historiaAporteCreateUseCase.execute(HistoriasCreateObserver(), HistoriaAporteCreateUseCase.Params(AporteCreationEntity(contenido = contenido, esBifurcable = esBifurcable), aporteSafe.id, historiaSafe.id))
            }
        }
    }

    private inner class HistoriasCreateObserver : DefaultObserver<AporteDetailEntity>() {

        override fun onError(exception: Throwable) {
            super.onError(exception)
        }

        override fun onComplete() {
        }

        override fun onSuccess(t: AporteDetailEntity) {
            creationComplete(t)
        }

    }

    private fun creationComplete(aporte: AporteDetailEntity) {
        this.historiaAporteCreateView?.toHistoriaDetail()
    }
}