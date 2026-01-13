package biz.ei6.eluvplages.screens

import androidx.compose.runtime.Composable

import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import biz.ei6.eluvplages.domain.Plage



@Composable
fun AppNav3() {

  val backStack = rememberNavBackStack(Screen.Home)

    fun goTop(tab : Screen) {
        backStack.clear()
        backStack.add(tab)

    }

    NavDisplay(
        backStack =backStack,
        entryProvider = entryProvider {

            entry<Screen.Home> {
                ListePlageScreen(

                    onEventClick = { e -> backStack.add(Screen.PlageDetail(e.id.toString())) },

                    onBottomNav = { label ->
                        when (label) {
                            "Accueil" -> goTop(Screen.Home)
                            "Carte" -> goTop(Screen.Map)
                            "Favoris" -> goTop(Screen.Favorites)
                            "Profil" -> goTop(Screen.Profile)
                        }
                    }
                )
            }

            entry<Screen.PlageDetail> { key ->
                val event = Plage.LISTE.firstOrNull { it.id.toString() == key.eventId }
                    ?: Plage.DEFAULT

                DetailsPlageScreen(

                    current = event,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<Screen.Map> {           }
            entry<Screen.Favorites> { /* … */ }
            entry<Screen.Profile> { /* … */ }
        }
    )
}
