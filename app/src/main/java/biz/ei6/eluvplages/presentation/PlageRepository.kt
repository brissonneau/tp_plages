package biz.ei6.eluvplages.presentation

import biz.ei6.eluvplages.domain.Plage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface PlageRepository {
    val plages : Flow<List<Plage>>
    suspend fun toggleFavorite(id:String)
    suspend fun add(plage : Plage)
    fun init()
}

