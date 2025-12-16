package com.dyusov.news.data.repo

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.dyusov.news.domain.entity.Language
import com.dyusov.news.domain.entity.Settings
import com.dyusov.news.domain.entity.toInterval
import com.dyusov.news.domain.repo.SettingsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// delegate with singleton double check
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsRepositoryImpl @Inject constructor(
    // @param: -> apply annotation only to param
    @param:ApplicationContext private val context: Context
) : SettingsRepository {

    // keys for DataStore
    private val languageKey = stringPreferencesKey("language")
    private val intervalKey = intPreferencesKey("interval")
    private val notificationsEnabledKey = booleanPreferencesKey("notificationsEnabled")
    private val wifiEnabledKey = booleanPreferencesKey("wifiEnabled")

    override fun getSettings(): Flow<Settings> {
        return context.dataStore.data.map { preferences ->
            Settings(
                language = Language.valueOf(
                    preferences[languageKey]
                        ?: Settings.DEFAULT_LANGUAGE.name
                ),
                interval = preferences[intervalKey]?.toInterval()
                    ?: Settings.DEFAULT_INTERVAL,
                notificationsEnabled = preferences[notificationsEnabledKey]
                    ?: Settings.DEFAULT_NOTIFICATIONS_ENABLED,
                wifiOnly = preferences[wifiEnabledKey]
                    ?: Settings.DEFAULT_WIFI_ONLY,
            )
        }
    }

    override suspend fun updateLanguage(language: Language) {
        context.dataStore.edit { preferences ->
            preferences[languageKey] = language.name
        }
    }

    override suspend fun updateInterval(minutes: Int) {
        context.dataStore.edit { preferences ->
            preferences[intervalKey] = minutes
        }
    }

    override suspend fun updateNotificationsEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[notificationsEnabledKey] = enabled
        }
    }

    override suspend fun updateWifiEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[wifiEnabledKey] = enabled
        }
    }
}