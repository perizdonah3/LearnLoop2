package com.periz.learnloop.models

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// DataStore instance (IMPORTANT: must be top-level)
val Context.dataStore by preferencesDataStore(name = "settings")

class SettingsDataStore(private val context: Context) {

    companion object {
        val DARK_MODE = booleanPreferencesKey("dark_mode")
    }

    val darkModeFlow: Flow<Boolean> = context.dataStore.data
        .map { prefs ->
            prefs[DARK_MODE] ?: false
        }

    suspend fun setDarkMode(enabled: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[DARK_MODE] = enabled
        }
    }
}