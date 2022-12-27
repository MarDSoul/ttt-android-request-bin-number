package app.mardsoul.requestbin.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import app.mardsoul.requestbin.app
import app.mardsoul.requestbin.databinding.FragmentSearchBinding
import app.mardsoul.requestbin.ui.BaseFragment

class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val viewModel: SearchViewModel by viewModels {
        SearchViewModelFactory(requireContext().app.historyUseCase)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.searchTestButton.setOnClickListener {
            val navDirection =
                SearchFragmentDirections.actionSearchFragmentToDetailsFragment("000000")
            findNavController().navigate(navDirection)
        }
    }
}