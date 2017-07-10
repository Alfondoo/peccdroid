package com.javialonso.peccdroid.presentation.presenter

import android.support.annotation.NonNull
import com.javialonso.peccdroid.data.entity.AporteDetailEntity
import com.javialonso.peccdroid.domain.interactor.AportesPendientesUseCase
import com.javialonso.peccdroid.domain.interactor.DefaultObserver
import com.javialonso.peccdroid.domain.interactor.ValidacionAportesUseCase
import com.javialonso.peccdroid.presentation.internal.PerActivity
import com.javialonso.peccdroid.presentation.view.fragment.AportesPendientesView
import javax.inject.Inject

@PerActivity
class AportesPendientesPresenter
@Inject constructor(private val aportesPendientesUseCase: AportesPendientesUseCase, private val validacionAportesUseCase: ValidacionAportesUseCase) : Presenter {
    private var aportesPendientesView: AportesPendientesView? = null
    private var historiaId: Int? = null

    fun setView(@NonNull view: AportesPendientesView) {
        this.aportesPendientesView = view
    }

    override fun resume() {}

    override fun pause() {}

    override fun destroy() {
        this.aportesPendientesView = null
    }

    fun aportesPendientes() {
        historiaId?.let {
            this.aportesPendientesUseCase.execute(HistoriasObserver(), AportesPendientesUseCase.Params(it))
        }
    }

    private inner class HistoriasObserver : DefaultObserver<List<AporteDetailEntity>>() {

        override fun onError(exception: Throwable) {
            super.onError(exception)
        }

        override fun onComplete() {
        }

        override fun onSuccess(t: List<AporteDetailEntity>) {
            updateView(t)
        }

    }

    private fun updateView(aportes: List<AporteDetailEntity>) {
        this.aportesPendientesView?.loadAportes(aportes)
    }

    fun aceptar(aporteDetailEntity: AporteDetailEntity) {
        this.validar(aporteDetailEntity, true)
    }

    fun rechazar(aporteDetailEntity: AporteDetailEntity) {
        this.validar(aporteDetailEntity, false)
    }

    private fun validar(aporteDetailEntity: AporteDetailEntity, validacion: Boolean) {
        historiaId?.let {
            this.validacionAportesUseCase.execute(ValidacionObserver(), ValidacionAportesUseCase.Params(it, aporteDetailEntity.id, validacion))
        }
    }

    private inner class ValidacionObserver : DefaultObserver<AporteDetailEntity>() {

        override fun onError(exception: Throwable) {
            super.onError(exception)
        }

        override fun onComplete() {
        }

        override fun onSuccess(t: AporteDetailEntity) {
            updateAporte(t)
        }
    }

    private fun updateAporte(aporte: AporteDetailEntity) {
        this.aportesPendientesView?.deleteAporte(aporte)
    }

    fun setHistoriaId(@NonNull id: Int) {
        this.historiaId = id
    }
}