package app.mardsoul.requestbin.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import app.mardsoul.requestbin.domain.HistoryUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn

class SearchViewModel(private val historyUseCase: HistoryUseCase) : ViewModel() {

    val requestHistoryList = historyUseCase.getHistoryRequests()
        .shareIn(viewModelScope, SharingStarted.Eagerly)
}

class SearchViewModelFactory(private val historyUseCase: HistoryUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchViewModel(historyUseCase) as T
    }
}