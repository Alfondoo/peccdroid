package com.javialonso.peccdroid.presentation.view.contract

import com.javialonso.peccdroid.data.entity.HistoriaEntity


interface HistoriasView : BaseView {

    fun updateHistoriasList(historias: List<HistoriaEntity>)

    fun toDetailHistoria(historia: HistoriaEntity)

    fun toCreateHistoria()

}
