package com.example.newsapplication.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.newsapplication.domain.UserEntryManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.prefs.Preferences
import javax.sql.DataSource

class UserEntryImpl(val context: Context) :UserEntryManager{
    val Context.datastore by preferencesDataStore(
    name = "prefs"
    )
    override suspend fun setUserEntry() {
      context.datastore.edit {prefs->
          prefs[booleanPreferencesKey(PrefKeys.HAS_ENTERED_ALREADY)]=true
      }

    }

    override fun getUserEntry(): Flow<Boolean> {
        return context.datastore.data.map {prefs->
        val data=prefs[booleanPreferencesKey(PrefKeys.HAS_ENTERED_ALREADY)]
            data?:false
        }
    }
}
object PrefKeys{
    const val HAS_ENTERED_ALREADY="already_entered"

}
