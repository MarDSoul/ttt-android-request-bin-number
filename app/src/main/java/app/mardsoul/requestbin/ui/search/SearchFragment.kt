package app.mardsoul.requestbin.ui.search

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import app.mardsoul.requestbin.R
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
                sendRequestOrShowToast { searchBinInformation(editText.text.toString()) }
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

    @RequiresPermission(value = "android.permission.ACCESS_NETWORK_STATE")
    private fun isOnline(): Boolean {
        val connectManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connectManager.activeNetworkInfo
        return networkInfo?.isConnected == true
    }

    private fun sendRequestOrShowToast(action: () -> Unit) {
        if (isOnline()) {
            action()
        } else {
            Toast.makeText(
                requireContext(),
                getString(R.string.no_internet_error_string),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}