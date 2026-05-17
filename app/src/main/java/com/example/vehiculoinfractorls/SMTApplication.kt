package com.example.vehiculoinfractorls

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class SMTApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        val modoOscuro = prefs.getBoolean(KEY_DARK_MODE, false)
        AppCompatDelegate.setDefaultNightMode(
            if (modoOscuro) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
    }

    companion object {
        const val PREFS_NAME = "SMT_PREFS"
        const val KEY_DARK_MODE = "dark_mode"
    }
}