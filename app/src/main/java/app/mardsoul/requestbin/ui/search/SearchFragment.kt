package app.mardsoul.requestbin.ui.search

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.annotation.StringRes
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import app.mardsoul.requestbin.R
import app.mardsoul.requestbin.app
import app.mardsoul.requestbin.databinding.FragmentSearchBinding
import app.mardsoul.requestbin.domain.entities.RequestHistoryItem
import app.mardsoul.requestbin.ui.BaseFragment

class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate),
    OnClickItemHistory {

    private val viewModel: SearchViewModel by viewModels {
        SearchViewModelFactory(requireContext().app.historyUseCase)
    }

    private val adapter = HistoryAdapter(this as OnClickItemHistory)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        lifecycleScope.launchWhenResumed {
            viewModel.requestHistoryList
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect {
                    adapter.setHistoryList(it)
                }
        }
    }

    private fun initViews() {
        with(binding) {
            historyRecyclerView.adapter = adapter
            outlinedTextField.setEndIconOnClickListener {
                sendRequestOrShowToast(editText.text.toString())
            }
            editText.setOnEditorActionListener { textView, id, keyEvent ->
                if (id == EditorInfo.IME_ACTION_SEARCH) {
                    sendRequestOrShowToast(textView.text.toString())
                    true
                } else {
                    false
                }
            }
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

    private fun sendRequestOrShowToast(binNumber: String) {
        if (isOnline()) {
            checkBinOrShowToast(binNumber)
        } else {
            showErrorToast(R.string.no_internet_error_string)
        }
    }

    private fun checkBinOrShowToast(binNumber: String) {
        if (checkBinNumber(binNumber)) {
            searchBinInformation(binNumber)
        } else {
            showErrorToast(R.string.incorrect_bin_error_string)
        }
    }

    private fun checkBinNumber(binNumber: String): Boolean {
        return binNumber.length in 6..8
    }

    private fun showErrorToast(@StringRes errorMessageId: Int) {
        Toast.makeText(
            requireContext(),
            getString(errorMessageId),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onClick(view: View) {
        val requestHistoryItem = view.tag as RequestHistoryItem
        if (requestHistoryItem.isRequestSuccess) {
            sendRequestOrShowToast(requestHistoryItem.binNumber)
        } else {
            showErrorToast(R.string.incorrect_bin_error_string)
        }
    }
}

interface OnClickItemHistory : View.OnClickListener