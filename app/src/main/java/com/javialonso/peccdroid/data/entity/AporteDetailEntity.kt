package com.javialonso.peccdroid.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class AporteDetailEntity(
        @Expose val id: Int,
        @Expose val contenido: String,
        @Expose @SerializedName("aporte_padre") val aportePadre: Int?,
        @Expose @SerializedName("es_aceptado") val esAceptado: Boolean,
        @Expose @SerializedName("es_bifurcable") val esBifurcable: Boolean,
        @Expose @SerializedName("puntuacion_media") val puntuacionMedia: Float,
        @Expose val creacion: Date
)