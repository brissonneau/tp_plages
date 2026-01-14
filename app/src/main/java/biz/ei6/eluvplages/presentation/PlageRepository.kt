package biz.ei6.eluvplages.presentation

import biz.ei6.eluvplages.domain.Plage
import kotlinx.coroutines.flow.StateFlow

interface PlageRepository {
    val plages : StateFlow<List<Plage>>
    fun toggleFavorite(id:String)
    fun add(plage : Plage)
}

