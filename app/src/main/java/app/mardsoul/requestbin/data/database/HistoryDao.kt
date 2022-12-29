package app.mardsoul.requestbin.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRequest(requestHistoryEntity: RequestHistoryEntity)

    @Query("SELECT * FROM ${RequestHistoryEntity.TABLE_NAME}")
    fun getAllHistory(): Flow<List<RequestHistoryEntity>>
}