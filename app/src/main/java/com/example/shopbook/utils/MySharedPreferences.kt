package com.example.shopbook.utils

import android.content.Context
import android.content.SharedPreferences

object MySharedPreferences {
    private const val sharedPreferencesName = "myPreference"
    private lateinit var sharedPreferences: SharedPreferences
    const val ACCESS_TOKEN = "access_token"
    fun init(context: Context) {
        sharedPreferences =
            context.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)
    }

    fun getString(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    fun putAccessToken(value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(ACCESS_TOKEN, value)
        editor.apply()
    }

    fun getAccessToken(defaultValue: String?): String? {
        return sharedPreferences.getString(ACCESS_TOKEN, defaultValue) ?: defaultValue
    }

    fun putString(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    fun putBoolean(key: String, value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun putInt(key: String, value: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getInt(key: String, defaultValue: Int): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    fun clearAccessToken() {
        val editor = sharedPreferences.edit()
        editor.remove(ACCESS_TOKEN)
        editor.apply()
    }
}