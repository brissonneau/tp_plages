package biz.ei6.eluvplages.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import biz.ei6.eluvplages.domain.Plage
import kotlinx.coroutines.flow.Flow

@Dao
interface PlageDao {
    @Query("SELECT * FROM plages")
    fun getAll(): Flow<List<Plage>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(plage : Plage)

    @Query("UPDATE plages SET isFavorite = CASE WHEN isFavorite = 1 THEN 0 ELSE 1 END WHERE id = :id")
    suspend fun toggleFavorite(id: String)

}