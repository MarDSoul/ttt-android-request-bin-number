package app.mardsoul.requestbin.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.mardsoul.requestbin.domain.SearchUseCase

class DetailsViewModel(private val searchUseCase: SearchUseCase):ViewModel() {
}

class DetailsViewModelFactory(private val searchUseCase: SearchUseCase):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsViewModel(searchUseCase) as T
    }
}