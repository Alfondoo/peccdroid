package com.javialonso.peccdroid.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class HistoriaCreationEntity(
        @Expose val titulo: String,
        @Expose @SerializedName("limite_caracteres") val limiteCaracteres: Int? = null,
        @Expose @SerializedName("minimo_caracteres") val minimoCaracteres: Int? = null,
        @Expose @SerializedName("criterios_aceptacion") val criteriosAceptacion: String? = null,
        @Expose @SerializedName("reglas_aceptacion") val reglasAceptacion: ReglasAceptacion? = ReglasAceptacion.Automatico,
        @Expose @SerializedName("reglas_aportes") val reglasAportes: ReglasAportes? = ReglasAportes.SinReglas,
        @Expose val aporte: AportePadreEntity
)