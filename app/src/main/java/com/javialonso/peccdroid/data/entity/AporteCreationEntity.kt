package com.javialonso.peccdroid.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AporteCreationEntity(
        @Expose val contenido: String,
        @Expose @SerializedName("es_bifurcable") val esBifurcable: Boolean
)