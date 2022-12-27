package app.mardsoul.requestbin.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [RequestHistoryEntity::class],
    version = 1,
    exportSchema = true
)
abstract class HistoryDatabase : RoomDatabase() {
    abstract fun getHistoryDao(): HistoryDao
}