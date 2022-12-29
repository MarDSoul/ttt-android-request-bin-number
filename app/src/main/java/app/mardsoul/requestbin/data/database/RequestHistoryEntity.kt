package app.mardsoul.requestbin.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.mardsoul.requestbin.data.database.RequestHistoryEntity.Companion.TABLE_NAME
import app.mardsoul.requestbin.domain.entities.RequestHistoryItem

@Entity(tableName = TABLE_NAME)
data class RequestHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_NAME_ID)
    val id: Int = 0,

    @ColumnInfo(name = COLUMN_NAME_TIME_REQUEST)
    val timeRequest: Long,

    @ColumnInfo(name = COLUMN_NAME_BIN_NUMBER)
    val binNumber: String,

    @ColumnInfo(name = COLUMN_NAME_IS_SUCCESS)
    val isRequestSuccess: Boolean
) {

    fun toRequestHistoryItem(): RequestHistoryItem {
        return RequestHistoryItem(
            id = id,
            timeRequest = timeRequest,
            binNumber = binNumber,
            isRequestSuccess = isRequestSuccess
        )
    }

    companion object {
        const val TABLE_NAME = "request_history"
        const val COLUMN_NAME_ID = "id"
        const val COLUMN_NAME_TIME_REQUEST = "time_request"
        const val COLUMN_NAME_BIN_NUMBER = "bin_number"
        const val COLUMN_NAME_IS_SUCCESS = "is_success"
    }
}