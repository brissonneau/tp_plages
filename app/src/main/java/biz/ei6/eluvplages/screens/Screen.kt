package biz.ei6.eluvplages.screens

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen : NavKey {

    @Serializable data object Home : Screen
    @Serializable data object Map : Screen
    @Serializable data object Favorites : Screen
    @Serializable data object Profile : Screen

    @Serializable data class PlageDetail(val eventId: String) : Screen
    @Serializable data object PlageEdit: Screen


}
