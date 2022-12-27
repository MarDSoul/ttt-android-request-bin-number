package app.mardsoul.requestbin.data

import app.mardsoul.requestbin.data.network.BinlistApi
import app.mardsoul.requestbin.domain.SearchUseCase

class SearchUseCaseImpl(private val binlistApi: BinlistApi) : SearchUseCase {
    override suspend fun getBinInformation(binNumber: String) {
        TODO("Not yet implemented")
    }
}