package biz.ei6.eluvplages.screens

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import biz.ei6.eluvplages.domain.Plage
import biz.ei6.eluvplages.presentation.PlageVM
import biz.ei6.eluvplages.presentation.ProfileVM


@Composable
fun AppNav3(navVM: PlageVM = viewModel()) {

    // Gère le bouton système “Back”
    BackHandler(enabled = navVM.backStack.size > 1) {
        navVM.pop()
    }

    NavDisplay(
        backStack = navVM.backStack,
        entryProvider = entryProvider {

            entry<Screen.Home> {
                ListePlageScreen(

                    vm = navVM,
                    onEventClick = { e -> navVM.push(Screen.PlageDetail(e.id.toString())) },
                    onFabClick = { navVM.push(Screen.PlageEdit) },
                    onBottomNav = { label ->
                        when (label) {
                            "Accueil" -> navVM.goTop(Screen.Home)
                            "Carte" ->navVM.goTop(Screen.Map)
                            "Favoris" -> navVM.goTop(Screen.Favorites)
                            "Profil" -> navVM.goTop(Screen.Profile)
                        }
                    }
                )
            }

            entry<Screen.PlageDetail> { key ->
                val event = Plage.LISTE.firstOrNull { it.id.toString() == key.eventId }
                    ?: Plage.DEFAULT

                DetailsPlageScreen(

                    current = event,
                    onBack = { navVM.pop() }
                )
            }

            entry<Screen.PlageEdit> { key ->

                EditPlageScreen(
                    vm = navVM,
                    onBack = { navVM.pop() }
                )
            }

            entry<Screen.Map> { MapPlageScreen(navVM.plages,
                onBack = { navVM.goTop(Screen.Home ) })
            }
            entry<Screen.Favorites> { /* … */ }

            entry<Screen.Profile> { ProfileScreen(onBack = { navVM.goTop(Screen.Home ) } ) }
        }
    )
}
