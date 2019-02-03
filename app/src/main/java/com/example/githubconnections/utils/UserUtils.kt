package com.example.githubconnections.utils

import android.content.Context
import android.preference.PreferenceManager

// TODO inject this
class UserUtils(private val context: Context?) {

    fun setUserLoggedIn(username: String) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().putBoolean("logged", true).apply()
        prefs.edit().putString("loggedUser", username).apply()
    }

    fun setUserLoggedOut() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().putBoolean("logged", false).apply()
    }

    fun isLoggedIn(): Boolean {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getBoolean("logged", false)
    }

    fun getLoggedUser() : String? {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getString("loggedUser", null)
    }
}