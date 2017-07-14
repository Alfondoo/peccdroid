package com.javialonso.peccdroid.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Javi on 05/07/2017.
 */
data class AportePadreEntity(
        @Expose val contenido: String,
        @Expose @SerializedName("es_bifurcable") val esBifurcable: Boolean
)