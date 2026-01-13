package biz.ei6.eluvplages.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import biz.ei6.eluvplages.domain.Plage
import biz.ei6.eluvplages.screens.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PlageVM : ViewModel() {
    val backStack: SnapshotStateList<Screen> = mutableStateListOf(Screen.Home)

    fun push(screen: Screen) {
        backStack.add(screen)
    }

    fun pop(): Boolean {
        // retourne true si on a effectivement pop
        return if (backStack.size > 1) {
            backStack.removeAt(backStack.lastIndex)
            true
        } else {
            false
        }
    }

    fun goTop(tab: Screen) {
        backStack.clear()
        backStack.add(tab)
    }

    private val _plages = MutableStateFlow<List<Plage>>(Plage.LISTE)
    val plages: StateFlow<List<Plage>> = _plages

    fun toggleFavorite(id: String) {
        _plages.value = _plages.value.map {
            if (it.id == id) {
                it.copy(isFavorite = !it.isFavorite)
            } else {
                it
            }
        }
    }


}