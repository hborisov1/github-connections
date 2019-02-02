package com.example.githubconnections.utils

import android.content.Context
import android.preference.PreferenceManager

class SharedPrefsUtils(val context: Context?) {

    fun setUserLoggedIn() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().putBoolean("logged", true).apply()
    }

    fun setUserLoggedOut() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().putBoolean("logged", false).apply()
    }

    fun isLoggedIn(): Boolean {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getBoolean("logged", false)
    }
}