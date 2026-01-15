package biz.ei6.eluvplages.data

import biz.ei6.eluvplages.domain.Plage
import biz.ei6.eluvplages.presentation.PlageRepository
import kotlinx.coroutines.flow.MutableStateFlow
/*
class InMemoryPlageRepository : PlageRepository {
    private val _plages = MutableStateFlow<List<Plage>>(Plage.LISTE)
    override val plages = _plages

    override fun toggleFavorite(id: String) {
       _plages.value = _plages.value.map {
            if (it.id == id) {
               it.copy(isFavorite = !it.isFavorite)
           } else {
           it
           }
       }
    }

    override fun add(plage: Plage) {
        _plages.value += plage
    }


}*/

