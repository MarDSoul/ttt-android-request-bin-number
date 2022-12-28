package app.mardsoul.requestbin.data

import app.mardsoul.requestbin.data.network.BinlistApi
import app.mardsoul.requestbin.domain.SearchUseCase
import app.mardsoul.requestbin.domain.entities.dto.BinInformationDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchUseCaseImpl(private val binlistApi: BinlistApi) : SearchUseCase {
    override suspend fun getBinInformation(binNumber: String): BinInformationDto? {
        return withContext(Dispatchers.IO) {
            val response = binlistApi.getBinInformation(binNumber)
            if (response.isSuccessful && response.body() != null) {
                return@withContext response.body()!!
            } else {
                return@withContext null
            }
        }
    }
}