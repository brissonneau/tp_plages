package biz.ei6.eluvplages.data


import android.content.Context
import biz.ei6.eluvplages.domain.Plage
import biz.ei6.eluvplages.presentation.PlageRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import java.io.File
import java.util.concurrent.atomic.AtomicBoolean

class FilePlageRepository(
    context: Context,
    private val fileName: String = "plages.txt",
    private val io: CoroutineDispatcher = Dispatchers.IO
) : PlageRepository {

    private val file = File(context.filesDir, fileName)
    private val tmpFile = File(context.filesDir, "$fileName.tmp")

    private val loaded = AtomicBoolean(false)

    private val _plages = MutableStateFlow<List<Plage>>(emptyList())
    override val plages: StateFlow<List<Plage>> = _plages.asStateFlow()

    override suspend fun toggleFavorite(id: String) {
        ensureLoaded()

        // maj mémoire
        val updated = _plages.value.map { p ->
            if (p.id == id) p.copy(isFavorite = !p.isFavorite) else p
        }
        _plages.value = updated

        // persistance
        withContext(io) { writeAllAtomic(updated) }
    }

    override suspend fun add(plage: Plage) {
        ensureLoaded()

        // maj mémoire
        val updated = _plages.value + plage
        _plages.value = updated

        // persistance
        withContext(io) { writeAllAtomic(updated) }
    }



    override fun init() {
      _plages.value =  readAllOrNull()
    }
    // -------------------- Private --------------------

    private suspend fun ensureLoaded() {
        if (loaded.get()) return

        val initial = withContext(io) { readAllOrNull() } ?: Plage.LISTE
        _plages.value = initial

        // Optionnel : si fichier absent, on le crée
        withContext(io) {
            if (!file.exists()) writeAllAtomic(initial)
        }

        loaded.set(true)
    }

    private fun readAllOrNull(): List<Plage> {
        if (!file.exists()) {
            val retval = Plage.LISTE
            writeAllAtomic(retval)
            return retval
        }

        val lines = file.readLines()
            .map { it.trim() }
            .filter { it.isNotBlank() }

        if (lines.isEmpty()) return emptyList()

        // ignore les lignes corrompues
        return lines.mapNotNull { line ->
            runCatching { line.toPlage() }.getOrNull()
        }
    }

    private fun writeAllAtomic(plages: List<Plage>) {
        val content = plages.joinToString("\n") { it.toStorageString() }

        tmpFile.writeText(content)

        if (file.exists()) file.delete()

        val ok = tmpFile.renameTo(file)
        if (!ok) {
            // fallback si rename échoue
            file.writeText(content)
            tmpFile.delete()
        }
    }
}
