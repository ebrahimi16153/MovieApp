package com.github.ebrahimi16153.movieapp.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.github.ebrahimi16153.movieapp.utils.Constants.USER_TOKEN_DATASTORE_KEY
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


class UserTokenDataStore @Inject constructor(@ApplicationContext private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(Constants.USER_TOKEN_DATASTORE_NAME)
        val userToke = stringPreferencesKey(USER_TOKEN_DATASTORE_KEY)

    }

    suspend fun saveUserToken(token: String) {
        context.dataStore.edit {
            it[userToke] = token
        }
    }


    val userToken = context.dataStore.data.map {
        it[userToke] ?: ""
    }


}