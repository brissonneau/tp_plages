package biz.ei6.eluvplages.data

import biz.ei6.eluvplages.datasource.PlageDao
import biz.ei6.eluvplages.domain.Plage
import biz.ei6.eluvplages.presentation.PlageRepository
import kotlinx.coroutines.flow.Flow

class RoomPlageRepository(private val dao: PlageDao): PlageRepository {

    override val plages: Flow<List<Plage>> = dao.getAll()

    override suspend fun toggleFavorite(id: String) {
        dao.toggleFavorite(id)
    }

    override suspend fun add(plage: Plage) {
       dao.upsert(plage)
    }

    override fun init() {
       // rien la base sera vide au début
    }

}

