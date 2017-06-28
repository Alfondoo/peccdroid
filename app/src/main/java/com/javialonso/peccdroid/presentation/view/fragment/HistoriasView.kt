package com.javialonso.peccdroid.presentation.view.fragment

import com.javialonso.peccdroid.data.entity.HistoriaEntity


interface HistoriasView : BaseView {

    fun updateHistoriasList(historias: List<HistoriaEntity>)

}
