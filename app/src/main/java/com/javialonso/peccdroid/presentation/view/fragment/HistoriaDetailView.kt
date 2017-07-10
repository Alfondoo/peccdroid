package com.javialonso.peccdroid.presentation.view.fragment

import com.javialonso.peccdroid.data.entity.AporteDetailEntity
import com.javialonso.peccdroid.data.entity.HistoriaDetailEntity

interface HistoriaDetailView {
    fun updateView(historia: HistoriaDetailEntity)
    fun showCreatorControls()
    fun toCreateNuevoAporte(aporte: AporteDetailEntity, historia: HistoriaDetailEntity)
    fun showAportesUntil(position: Int)
    fun toAportesPendientes(historia: Int)
}