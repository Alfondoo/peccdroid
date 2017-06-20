package com.javialonso.peccdroid.data.entity

import com.google.gson.annotations.SerializedName

data class RegistrationEntity(val username: String, val email: String, val password: String, @SerializedName("confirmacion_password") val passwordConfirmation: String)