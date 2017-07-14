package com.javialonso.peccdroid.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class AporteProfileEntity(
        @Expose val contenido: String,
        @Expose val historia: HistoriaEntity,
        @Expose @SerializedName("es_aceptado") val esAceptado: Boolean,
        @Expose @SerializedName("es_bifurcable") val esBifurcable: Boolean,
        @Expose @SerializedName("puntuacion_media") val puntuacionMedia: Float,
        @Expose val creacion: Date
)