package app.mardsoul.requestbin.data

import app.mardsoul.requestbin.data.database.HistoryDao
import app.mardsoul.requestbin.data.database.RequestHistoryEntity
import app.mardsoul.requestbin.domain.HistoryUseCase
import app.mardsoul.requestbin.domain.entities.RequestHistoryItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class HistoryUseCaseImpl(private val historyDao: HistoryDao) : HistoryUseCase {
    override fun getHistoryRequests(): Flow<List<RequestHistoryItem>> {
        return historyDao.getAllHistory()
            .map {
                it.map { historyEntity -> historyEntity.toRequestHistoryItem() }
            }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun saveRequest(requestHistoryItem: RequestHistoryItem) {
        withContext(Dispatchers.IO) {
            historyDao.saveRequest(convertItemToEntity(requestHistoryItem))
        }
    }

    private fun convertItemToEntity(requestHistoryItem: RequestHistoryItem): RequestHistoryEntity {
        return RequestHistoryEntity(
            timeRequest = requestHistoryItem.timeRequest,
            binNumber = requestHistoryItem.binNumber,
            isRequestSuccess = requestHistoryItem.isRequestSuccess
        )
    }
}