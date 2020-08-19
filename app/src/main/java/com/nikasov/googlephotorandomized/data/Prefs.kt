package com.nikasov.googlephotorandomized.data

import android.content.Context
import android.preference.PreferenceManager
import javax.inject.Inject

class Prefs  @Inject constructor (
    context: Context
) {

    companion object {
        const val FIRST_RUN = "first_run"
        const val TOKEN = "user_token"
    }

    private val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
    private val editor = sharedPref.edit()

    fun isLogged() = sharedPref.getBoolean(FIRST_RUN, false)
    fun getToken() = sharedPref.getString(TOKEN, "")

    fun saveIsLogged() {
        editor.apply {
            putBoolean(FIRST_RUN, true)
        }.apply()
    }

    fun saveToken(token: String) {
        editor.apply {
            putString(TOKEN, token)
        }.apply()
    }
}