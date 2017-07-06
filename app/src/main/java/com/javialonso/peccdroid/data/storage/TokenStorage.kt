package com.javialonso.peccdroid.data.storage

import com.javialonso.peccdroid.data.entity.TokenEntity

interface TokenStorage {
    fun saveData(tokenEntity: TokenEntity)

    fun retrieveData(): TokenEntity
}