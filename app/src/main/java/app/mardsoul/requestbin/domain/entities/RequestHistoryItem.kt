package app.mardsoul.requestbin.domain.entities

data class RequestHistoryItem(
    val id: Int = 0,
    val timeRequest: Long,
    val binNumber: Int,
    val isRequestSuccess: Boolean
)