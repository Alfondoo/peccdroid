package com.javialonso.peccdroid.data.entity

import com.google.gson.annotations.Expose

data class ProfileEntity(
        @Expose val username: String,
        val email: String,
        @Expose val historias: List<HistoriaEntity>,
        @Expose val aportes: List<AporteProfileEntity>,
        val puntuacionHistorias: List<String>,
        val puntuacionAportes: List<String>
)
