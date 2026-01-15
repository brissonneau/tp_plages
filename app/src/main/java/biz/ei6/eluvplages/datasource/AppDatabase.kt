package biz.ei6.eluvplages.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import biz.ei6.eluvplages.datasource.PlageDao
import biz.ei6.eluvplages.domain.Plage

@TypeConverters(Converters::class)
@Database(entities = [Plage::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun plageDao(): PlageDao
}


