package com.javialonso.peccdroid.presentation.view.fragment

import com.javialonso.peccdroid.data.entity.AporteDetailEntity

/**
 * Created by Javi on 07/07/2017.
 */
interface AportesPendientesView {
    fun loadAportes(aportes: List<AporteDetailEntity>)
    fun deleteAporte(aporte: AporteDetailEntity)

}