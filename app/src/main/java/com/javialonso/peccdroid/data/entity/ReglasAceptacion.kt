package com.javialonso.peccdroid.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

enum class ReglasAceptacion(val regla: String) {
    @Expose @SerializedName("A") Autor("A"),
    @Expose @SerializedName("X") Automatico("X")
}