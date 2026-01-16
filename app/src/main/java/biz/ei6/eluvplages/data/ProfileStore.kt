package biz.ei6.eluvplages.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "profile")

class ProfileStore(private val context: Context) {

    private val KEY_NAME = stringPreferencesKey("name")
    private val KEY_PHOTO_PATH = stringPreferencesKey("photo_path")

    val nameFlow: Flow<String> = context.dataStore.data.map { it[KEY_NAME] ?: "" }
    val photoPathFlow: Flow<String> = context.dataStore.data.map { it[KEY_PHOTO_PATH] ?: "" }

    suspend fun saveName(name: String) {
        context.dataStore.edit { it[KEY_NAME] = name }
    }

    suspend fun savePhotoPath(path: String) {
        context.dataStore.edit { it[KEY_PHOTO_PATH] = path }
    }
}
