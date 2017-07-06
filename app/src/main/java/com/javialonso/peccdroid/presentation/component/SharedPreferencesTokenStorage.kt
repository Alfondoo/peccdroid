package com.javialonso.peccdroid.presentation.component

import android.content.Context
import com.javialonso.peccdroid.data.entity.TokenEntity
import com.javialonso.peccdroid.data.storage.TokenStorage


class SharedPreferencesTokenStorage constructor(val context: Context) : TokenStorage {
    override fun saveData(tokenEntity: TokenEntity) {
        val sharedPref = this.context.getSharedPreferences("tokenPreferences", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("token", tokenEntity.token)
        editor.putString("username", tokenEntity.username)
        editor.apply()

    }

    override fun retrieveData(): TokenEntity {
        val sharedPref = this.context.getSharedPreferences("tokenPreferences", Context.MODE_PRIVATE)
        val token = sharedPref.getString("token", null)
        val username = sharedPref.getString("username", null)
        return TokenEntity(token, username)
    }
}