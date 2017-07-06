package com.javialonso.peccdroid.data.entity

import com.google.gson.annotations.Expose

data class TokenEntity(
        @Expose val token: String,
        @Expose val username: String
)
