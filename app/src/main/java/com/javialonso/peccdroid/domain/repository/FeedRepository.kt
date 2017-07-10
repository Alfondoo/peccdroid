package com.javialonso.peccdroid.domain.repository

import com.javialonso.peccdroid.data.entity.*
import io.reactivex.Maybe

interface FeedRepository {
    fun profile(): Maybe<ProfileEntity>
    fun historias(): Maybe<List<HistoriaEntity>>
    fun detailHistoria(id: Int): Maybe<HistoriaDetailEntity>
    fun createHistoria(historia: HistoriaCreationEntity): Maybe<HistoriaDetailEntity>
    fun createAporteHistoria(aporte: AporteCreationEntity, aportePadre: Int, historia: Int): Maybe<AporteDetailEntity>
    fun aportesPendientes(historia: Int): Maybe<List<AporteDetailEntity>>
    fun validar(historia: Int, aporte: Int, validacion: Boolean): Maybe<AporteDetailEntity>
}