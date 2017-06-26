package com.javialonso.peccdroid.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

enum class ReglasAportes(val regla: String){
    @Expose @SerializedName("L") MaxCaracteres("L"),
    @Expose @SerializedName("M") MinCaracteres("M"),
    @Expose @SerializedName("S") SinReglas("S"),
}