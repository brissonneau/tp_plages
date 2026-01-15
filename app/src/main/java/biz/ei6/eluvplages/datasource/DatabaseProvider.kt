package biz.ei6.eluvplages.datasource

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    @Volatile private var INSTANCE : AppDatabase? =null

    fun get(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "plages_db"
            ).build().also {INSTANCE = it}
        }
    }

}

