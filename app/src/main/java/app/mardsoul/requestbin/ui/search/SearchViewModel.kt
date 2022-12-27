package app.mardsoul.requestbin.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.mardsoul.requestbin.domain.HistoryUseCase
import app.mardsoul.requestbin.domain.SearchUseCase

class SearchViewModel(private val historyUseCase: HistoryUseCase) : ViewModel() {
}

class SearchViewModelFactory(private val historyUseCase: HistoryUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchViewModel(historyUseCase) as T
    }
}