package app.mardsoul.requestbin.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import app.mardsoul.requestbin.app
import app.mardsoul.requestbin.databinding.FragmentDetailsBinding
import app.mardsoul.requestbin.domain.entities.dto.BinInformationDto
import app.mardsoul.requestbin.ui.BaseFragment
import kotlinx.coroutines.launch

class DetailsFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    private val viewModel: DetailsViewModel by viewModels {
        DetailsViewModelFactory(requireContext().app.searchUseCase)
    }

    private val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        lifecycleScope.launch {
            viewModel.uiState.collect {
                bindInformation(it)
            }
        }
        viewModel.searchBinInformation(args.binNumber)

    }

    private fun bindInformation(binInformationDto: BinInformationDto?) {
        with(binding) {
            binNumberTextView.text = args.binNumber
            schemeTextView.text = binInformationDto?.scheme
        }
    }

    private fun initViews() {
        binding.backTestButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}