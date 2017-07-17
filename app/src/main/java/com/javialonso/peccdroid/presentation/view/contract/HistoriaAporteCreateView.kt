package com.javialonso.peccdroid.presentation.view.contract

import com.javialonso.peccdroid.data.entity.ReglasAportes

interface HistoriaAporteCreateView {
    fun toHistoriaDetail()
    fun updateReglas(reglasAportes: ReglasAportes, caracteres: Int)
    fun updateReglas(reglasAportes: ReglasAportes)
}