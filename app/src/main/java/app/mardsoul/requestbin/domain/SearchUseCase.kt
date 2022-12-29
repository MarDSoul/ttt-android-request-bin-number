package app.mardsoul.requestbin.domain

import app.mardsoul.requestbin.domain.entities.dto.BinInformationDto

interface SearchUseCase {
    suspend fun getBinInformation(binNumber: String): BinInformationDto?
}