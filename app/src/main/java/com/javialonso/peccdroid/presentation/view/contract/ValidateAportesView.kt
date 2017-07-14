package com.javialonso.peccdroid.presentation.view.contract

import com.javialonso.peccdroid.data.entity.AporteDetailEntity

/**
 * Created by Javi on 07/07/2017.
 */
interface ValidateAportesView {
    fun loadAportes(aportes: List<AporteDetailEntity>)
    fun deleteAporte(aporte: AporteDetailEntity)

}