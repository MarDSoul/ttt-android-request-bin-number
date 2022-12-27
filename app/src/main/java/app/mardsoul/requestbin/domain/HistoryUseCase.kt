package app.mardsoul.requestbin.domain

import app.mardsoul.requestbin.domain.entities.RequestHistoryItem
import kotlinx.coroutines.flow.Flow

interface HistoryUseCase {
    fun getHistoryRequests(): Flow<RequestHistoryItem>
    suspend fun saveRequest(requestHistoryItem: RequestHistoryItem)
}