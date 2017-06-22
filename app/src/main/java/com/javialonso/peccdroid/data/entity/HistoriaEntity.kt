package com.javialonso.peccdroid.data.entity

import com.google.gson.annotations.SerializedName

data class HistoriaEntity(val titulo: String, val votos: Int, @SerializedName("puntuacion_media") val puntuacionMedia: Float, val limiteCaracteres: Int?, val minimoCaracteres: Int?, @SerializedName("criterios_aceptacion") val criteriosAceptacion: String?, val creador: String?, val id: Int, @SerializedName("reglas_aceptacion") val reglasAceptacion: ReglasAceptacion)
