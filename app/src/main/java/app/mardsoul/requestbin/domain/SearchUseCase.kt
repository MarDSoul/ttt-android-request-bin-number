package app.mardsoul.requestbin.domain

interface SearchUseCase {
    suspend fun getBinInformation(binNumber: String)
}