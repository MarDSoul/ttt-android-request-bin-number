package app.mardsoul.requestbin.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import app.mardsoul.requestbin.domain.SearchUseCase
import app.mardsoul.requestbin.domain.entities.dto.BinInformationDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(private val searchUseCase: SearchUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<BinInformationDto?>(null)
    val uiState = _uiState.asStateFlow()

    fun searchBinInformation(binNumber: String) {
        viewModelScope.launch {
            val value = searchUseCase.getBinInformation(binNumber)
            _uiState.value = value
        }
    }
}

class DetailsViewModelFactory(private val searchUseCase: SearchUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsViewModel(searchUseCase) as T
    }
}