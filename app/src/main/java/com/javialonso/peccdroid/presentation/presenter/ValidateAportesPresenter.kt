package com.javialonso.peccdroid.presentation.presenter

import android.support.annotation.NonNull
import com.javialonso.peccdroid.data.entity.AporteDetailEntity
import com.javialonso.peccdroid.domain.interactor.AportesPendientesListUseCase
import com.javialonso.peccdroid.domain.interactor.DefaultObserver
import com.javialonso.peccdroid.domain.interactor.ValidateAportesUseCase
import com.javialonso.peccdroid.presentation.internal.PerActivity
import com.javialonso.peccdroid.presentation.view.contract.ValidateAportesView
import javax.inject.Inject

@PerActivity
class ValidateAportesPresenter
@Inject constructor(private val aportesPendientesListUseCase: AportesPendientesListUseCase, private val validateAportesUseCase: ValidateAportesUseCase) : Presenter {
    private var validateAportesView: ValidateAportesView? = null
    private var historiaId: Int? = null

    fun setView(@NonNull viewValidate: ValidateAportesView) {
        this.validateAportesView = viewValidate
    }

    override fun resume() {}

    override fun pause() {}

    override fun destroy() {
        this.validateAportesView = null
    }

    fun aportesPendientes() {
        historiaId?.let {
            this.aportesPendientesListUseCase.execute(HistoriasObserver(), AportesPendientesListUseCase.Params(it))
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
        this.validateAportesView?.loadAportes(aportes)
    }

    fun aceptar(aporteDetailEntity: AporteDetailEntity) {
        this.validar(aporteDetailEntity, true)
    }

    fun rechazar(aporteDetailEntity: AporteDetailEntity) {
        this.validar(aporteDetailEntity, false)
    }

    private fun validar(aporteDetailEntity: AporteDetailEntity, validacion: Boolean) {
        historiaId?.let {
            this.validateAportesUseCase.execute(ValidacionObserver(), ValidateAportesUseCase.Params(it, aporteDetailEntity.id, validacion))
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
        this.validateAportesView?.deleteAporte(aporte)
    }

    fun setHistoriaId(@NonNull id: Int) {
        this.historiaId = id
    }
}