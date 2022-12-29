package app.mardsoul.requestbin.ui.details

import app.mardsoul.requestbin.domain.entities.dto.BinInformationDto

data class DetailsUiState(
    val binNumber: String? = null,
    val binInformationDto: BinInformationDto? = null
)