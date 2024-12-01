package com.example.newsapplication.data.manager

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.newsapplication.domain.LocalUserManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImpl(val context: Context) :LocalUserManager{
    val Context.datastore by preferencesDataStore(
    name = "prefs"
    )
    override suspend fun setUserEntry() {
      context.datastore.edit {prefs->
          prefs[booleanPreferencesKey(PrefKeys.SHOW_ONBOARDING)]=false
      }

    }

    override fun getUserEntry(): Flow<Boolean> {
        return context.datastore.data.map {prefs->
        val data=prefs[booleanPreferencesKey(PrefKeys.SHOW_ONBOARDING)]
            data?:true
        }
    }
}
object PrefKeys{
    const val SHOW_ONBOARDING="show_onboarding"

}
