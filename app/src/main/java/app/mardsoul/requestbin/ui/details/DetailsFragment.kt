package app.mardsoul.requestbin.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import app.mardsoul.requestbin.app
import app.mardsoul.requestbin.databinding.FragmentDetailsBinding
import app.mardsoul.requestbin.ui.BaseFragment

class DetailsFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    private val viewModel: DetailsViewModel by viewModels {
        DetailsViewModelFactory(requireContext().app.searchUseCase)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.backTestButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}