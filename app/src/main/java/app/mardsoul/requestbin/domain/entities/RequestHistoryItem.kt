package app.mardsoul.requestbin.domain.entities

data class RequestHistoryItem(
    val id: Int = 0,
    val timeRequest: Long,
    val binNumber: String,
    val isRequestSuccess: Boolean = false
)