package app.mardsoul.requestbin.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import app.mardsoul.requestbin.app
import app.mardsoul.requestbin.databinding.FragmentSearchBinding
import app.mardsoul.requestbin.ui.BaseFragment

class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val viewModel: SearchViewModel by viewModels {
        SearchViewModelFactory(requireContext().app.historyUseCase)
    }

    private val adapter = HistoryAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        lifecycleScope.launchWhenResumed {
            viewModel.requestHistoryList.collect {
                adapter.setHistoryList(it)
            }
        }
    }

    private fun initViews() {
        with(binding) {
            historyRecyclerView.adapter = adapter
            outlinedTextField.setEndIconOnClickListener {
                searchBinInformation(editText.text.toString())
            }
        }


        binding.searchTestButton.setOnClickListener {
            val navDirection =
                SearchFragmentDirections.actionSearchFragmentToDetailsFragment("000000")
            findNavController().navigate(navDirection)
        }
    }

    private fun searchBinInformation(binNumber: String) {
        val navDirection =
            SearchFragmentDirections.actionSearchFragmentToDetailsFragment(binNumber)
        findNavController().navigate(navDirection)
    }
}