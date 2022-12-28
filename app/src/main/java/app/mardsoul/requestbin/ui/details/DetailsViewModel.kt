package app.mardsoul.requestbin.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import app.mardsoul.requestbin.domain.HistoryUseCase
import app.mardsoul.requestbin.domain.SearchUseCase
import app.mardsoul.requestbin.domain.entities.RequestHistoryItem
import app.mardsoul.requestbin.domain.entities.dto.BinInformationDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val searchUseCase: SearchUseCase,
    private val historyUseCase: HistoryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailsUiState())
    val uiState = _uiState.asStateFlow()

    fun searchBinInformation(binNumber: String) {
        viewModelScope.launch {
            if (uiState.value.binNumber != binNumber) {
                val value = searchUseCase.getBinInformation(binNumber)
                _uiState.value = DetailsUiState(binNumber, value)
                saveRequest(binNumber, value)
            }
        }
    }

    private suspend fun saveRequest(binNumber: String, binInformationDto: BinInformationDto?) {
        historyUseCase.saveRequest(
            RequestHistoryItem(
                timeRequest = System.currentTimeMillis(),
                binNumber = binNumber,
                isRequestSuccess = binInformationDto?.bankDto?.name != null
            )
        )
    }
}

class DetailsViewModelFactory(
    private val searchUseCase: SearchUseCase,
    private val historyUseCase: HistoryUseCase
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsViewModel(searchUseCase, historyUseCase) as T
    }
}