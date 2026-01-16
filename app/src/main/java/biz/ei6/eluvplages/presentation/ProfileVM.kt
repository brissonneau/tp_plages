package biz.ei6.eluvplages.presentation

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import biz.ei6.eluvplages.data.InternalImageStorage
import biz.ei6.eluvplages.data.ProfileStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.launch

data class ProfileUiState(
    val name: String = "",
    val photoPath: String = "",
    val photoBitmap: Bitmap? = null
)

class ProfileVM(app: Application) : AndroidViewModel(app) {

    private val store = ProfileStore(app)

    private val bitmapState = MutableStateFlow<Bitmap?>(null)

    val uiState: StateFlow<ProfileUiState> =
        combine(store.nameFlow, store.photoPathFlow, bitmapState) { name, path, bmp ->
            ProfileUiState(
                name = name,
                photoPath = path,
                photoBitmap = bmp ?: InternalImageStorage.loadBitmap(path)
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000),
            ProfileUiState())

    fun onNameChange(newName: String) {
        viewModelScope.launch { store.saveName(newName) }
    }

    fun onNewPhoto(bitmap: Bitmap) {
        val context = getApplication<Application>()
        viewModelScope.launch {
            val path = InternalImageStorage.saveProfileBitmap(context, bitmap)
            store.savePhotoPath(path)
            bitmapState.value = bitmap
        }
    }

    fun clearPhoto() {
        val context = getApplication<Application>()
        viewModelScope.launch {
            val currentPath = uiState.value.photoPath
            if (currentPath.isNotBlank()) InternalImageStorage.delete(currentPath)
            store.savePhotoPath("")
            bitmapState.value = null
        }
    }
}
