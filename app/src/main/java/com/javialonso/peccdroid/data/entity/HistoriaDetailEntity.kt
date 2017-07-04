package com.javialonso.peccdroid.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HistoriaDetailEntity(
        @Expose val titulo: String,
        @Expose val id: Int,
        @Expose val votos: Int,
        @Expose @SerializedName("puntuacion_media") val puntuacionMedia: Float,
        @Expose @SerializedName("limite_caracteres") val limiteCaracteres: Int,
        @Expose @SerializedName("minimo_caracteres") val minimoCaracteres: Int,
        @Expose @SerializedName("criterios_aceptacion") val criteriosAceptacion: String?,
        @Expose val creador: String,
        @Expose @SerializedName("reglas_aceptacion") val reglasAceptacion: ReglasAceptacion,
        @Expose @SerializedName("reglas_aportes") val reglasAportes: ReglasAportes,
        @Expose val aportes: List<AporteDetailEntity>
)