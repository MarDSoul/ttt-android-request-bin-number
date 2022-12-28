package app.mardsoul.requestbin.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import app.mardsoul.requestbin.R
import app.mardsoul.requestbin.app
import app.mardsoul.requestbin.databinding.FragmentDetailsBinding
import app.mardsoul.requestbin.domain.entities.dto.BinInformationDto
import app.mardsoul.requestbin.ui.BaseFragment
import app.mardsoul.requestbin.utils.convertYesNoString
import kotlinx.coroutines.launch

class DetailsFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    private val viewModel: DetailsViewModel by viewModels {
        DetailsViewModelFactory(
            requireContext().app.searchUseCase,
            requireContext().app.historyUseCase
        )
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
            schemeValueTextView.text =
                getString(R.string.scheme_value_text, binInformationDto?.scheme)
            brandValueTextView.text =
                getString(R.string.brand_value_text, binInformationDto?.brand)
            cardLengthValueTextView.text =
                getString(R.string.card_length_value_text, binInformationDto?.numberDto?.length)
            cardLuhnValueTextView.text =
                getString(
                    R.string.card_luhn_value_text,
                    binInformationDto?.numberDto?.luhn?.convertYesNoString()
                )
            typeValueTextView.text =
                getString(R.string.type_value_text, binInformationDto?.type)
            prepaidValueTextView.text =
                getString(
                    R.string.prepaid_value_text,
                    binInformationDto?.prepaid?.convertYesNoString()
                )
            countryValueTextView.text =
                getString(
                    R.string.country_value_text,
                    binInformationDto?.countryDto?.alpha2,
                    binInformationDto?.countryDto?.name
                )
            countryLatLonTextView.text =
                getString(
                    R.string.country_lat_lon_text,
                    binInformationDto?.countryDto?.latitude,
                    binInformationDto?.countryDto?.longitude
                )
            bankNameTextView.text =
                getString(R.string.bank_name_text, binInformationDto?.bankDto?.name)
            bankUrlTextView.text =
                getString(R.string.bank_url_text, binInformationDto?.bankDto?.url)
            bankPhoneTextView.text =
                getString(R.string.bank_phone_text, binInformationDto?.bankDto?.phone)
        }
    }

    private fun initViews() {
        binding.backTestButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}